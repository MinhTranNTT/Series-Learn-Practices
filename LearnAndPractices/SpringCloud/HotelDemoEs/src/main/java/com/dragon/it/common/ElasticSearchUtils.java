package com.dragon.it.common;

import com.alibaba.fastjson.JSON;
import com.dragon.it.pojo.HotelDoc;
import com.dragon.it.pojo.PageResult;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElasticSearchUtils {

    public static PageResult handleResponse(SearchResponse searchResponse) {
        SearchHits searchHits = searchResponse.getHits();
        long total = searchHits.getTotalHits().value;
        SearchHit[] hits = searchHits.getHits();
        List<HotelDoc> hotels = new ArrayList<>();

        Arrays.stream(hits)
                .forEach(hit -> {
                    HotelDoc hotelDoc = JSON.parseObject(hit.getSourceAsString(), HotelDoc.class);
                    Object[] sortValues = hit.getSortValues();
                    if (ArrayUtils.isNotEmpty(sortValues)) {
                        hotelDoc.setDistance(sortValues[0]);
                    }

                    hotels.add(hotelDoc);
                });
        System.out.println("Total: " + total);
        return new PageResult(total, hotels);
    }

    public static List<String> getAggregationByFieldName(Aggregations aggregation, String fieldNameAgg) {
        Terms fieldAgg = aggregation.get(fieldNameAgg);
        List<? extends Terms.Bucket> buckets = fieldAgg.getBuckets();
        List<String> result = buckets.stream()
                .map(MultiBucketsAggregation.Bucket::getKeyAsString)
                .collect(Collectors.toList());
        return result;
    }
}
