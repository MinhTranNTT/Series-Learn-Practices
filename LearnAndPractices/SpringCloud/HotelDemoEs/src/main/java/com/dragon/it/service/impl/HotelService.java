package com.dragon.it.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.it.common.ElasticSearchUtils;
import com.dragon.it.mapper.HotelMapper;
import com.dragon.it.pojo.Hotel;
import com.dragon.it.pojo.PageResult;
import com.dragon.it.pojo.RequestParams;
import com.dragon.it.service.IHotelService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.dragon.it.constants.HotelConstants.*;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired private RestHighLevelClient client;

    @Override
    public PageResult search(RequestParams params) {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        buildBasicStyleQuery(params, request);
        int page = params.getPage();
        int size = params.getSize();
        request.source().from((page - 1) * size).size(size);

        params.setLocation("31.15718, 121.572391");
        if (StringUtils.isNotEmpty(params.getLocation())) {
            request.source().sort(SortBuilders
                    .geoDistanceSort(PARAM_ES_LOCATION, new GeoPoint(params.getLocation()))
                    .order(SortOrder.DESC)
                    .unit(DistanceUnit.KILOMETERS)
            );
        }

        try {
            SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
            return ElasticSearchUtils.handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildBasicStyleQuery(RequestParams params, SearchRequest request) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        String key = params.getKey();
        if (StringUtils.isEmpty(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
            boolQuery.must(QueryBuilders.matchQuery(PARAM_ES_ALL, key));
        }

        if (StringUtils.isNotEmpty(params.getCity())) {
            boolQuery.filter(QueryBuilders.termQuery(PARAM_ES_CITY, params.getCity()));
        }
        if (StringUtils.isNotEmpty(params.getStarName())) {
            boolQuery.filter(QueryBuilders.termQuery(PARAM_ES_STAR_NAME, params.getStarName()));
        }
        if (StringUtils.isNotEmpty(params.getBrand())) {
            boolQuery.filter(QueryBuilders.termQuery(PARAM_ES_BRAND, params.getBrand()));
        }
        if (Objects.nonNull(params.getMinPrice()) && Objects.nonNull(params.getMaxPrice())) {
            boolQuery.filter(QueryBuilders
                    .rangeQuery(PARAM_ES_PRICE)
                    .gte(params.getMinPrice())
                    .lte(params.getMaxPrice()));
        }

        // function score
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(
                boolQuery,
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[] {
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                QueryBuilders.termQuery(PARAM_ES_IS_ADS, true),
                                ScoreFunctionBuilders.weightFactorFunction(10)
                        )
                }
        );

//        request.source().query(boolQuery);
        request.source().query(functionScoreQuery);
    }


    @Override
    public Map<String, List<String>> getFilters(RequestParams params) {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        buildBasicStyleQuery(params, request);
        // aggregation data, not get then set size = 0
        request.source().size(0);
        buildBasicAggregations(request);
        try {
            SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
            Map<String, List<String>> result = new HashMap<>();
            Aggregations aggregations = response.getAggregations();
            List<String> brandLst = ElasticSearchUtils.getAggregationByFieldName(aggregations, PARAM_ES_AGG_BRAND);
            result.put(PARAM_ES_BRAND, brandLst);

            List<String> cityList = ElasticSearchUtils.getAggregationByFieldName(aggregations, PARAM_ES_AGG_CITY);
            result.put(PARAM_ES_CITY, cityList);

            List<String> startLst = ElasticSearchUtils.getAggregationByFieldName(aggregations, PARAM_ES_AGG_STAR);
            result.put(PARAM_ES_STAR_NAME, startLst);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildBasicAggregations(SearchRequest request) {
        request.source().aggregation(AggregationBuilders
                .terms(PARAM_ES_AGG_BRAND)
                .field(PARAM_ES_BRAND)
                .size(ES_AGG_LIMIT));

        request.source().aggregation(AggregationBuilders
                .terms(PARAM_ES_AGG_CITY)
                .field(PARAM_ES_CITY)
                .size(ES_AGG_LIMIT));

        request.source().aggregation(AggregationBuilders
                .terms(PARAM_ES_AGG_STAR)
                .field(PARAM_ES_STAR_NAME)
                .size(ES_AGG_LIMIT));


    }


}
