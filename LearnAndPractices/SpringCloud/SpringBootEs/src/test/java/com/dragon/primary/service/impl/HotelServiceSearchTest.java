package com.dragon.primary.service.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.primary.pojo.HotelDoc;
import com.dragon.primary.service.IHotelService;
import com.dragon.springbootes.SpringBootEsApplication;
import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.dragon.primary.constants.HotelConstants.indexName;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringBootEsApplication.class)
class HotelServiceSearchTest {
    @Autowired private IHotelService hotelService;
    private RestHighLevelClient client;

    @BeforeEach
    public void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("localhost:9200")
        ));
    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }

    @Test
    public void init() {
        System.out.println(this.client);
    }

    @Test
    void testSearchMatchAll() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);

        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        SearchHit[] hits = searchHits.getHits();

        Arrays.stream(hits).forEach(hit -> {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            System.out.println(hotelDoc);
        });

        System.out.println("total: " + total);
    }

    @Test
    void testSearchMatchQuery() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source()
//              .query(QueryBuilders.matchQuery("all", "安交路")); // match
                .query(QueryBuilders.multiMatchQuery("安贞地", "name", "business"));
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }


    @Test
    void testTermSearchQuery() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.termQuery("city", "深圳"));
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void testRangeSearchQuery() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.rangeQuery("price").gte(100).lte(150));
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    private void handleResponse(SearchResponse searchResponse) {
        SearchHits searchHits = searchResponse.getHits();
        TotalHits totalHits = searchHits.getTotalHits();
        SearchHit[] hits = searchHits.getHits();

        Arrays.stream(hits)
                .forEach(hit -> {
                    String json = hit.getSourceAsString();
                    HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
                    System.out.println(hotelDoc);
                });
        System.out.println("Total: " + totalHits);
    }

    @Test
    void testBoolQuerySearch() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("city","深圳"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte("250"));
        request.source().query(boolQueryBuilder);
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void testSortSearch() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.matchQuery("all", "安交路"));
        request.source().sort("price", SortOrder.DESC);
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void testLimitOffSetSearch() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.matchQuery("all", "安交路"));
        request.source().sort("price", SortOrder.DESC);
        request.source().from(0).size(5);
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    // not show highlight
    @Test
    void testHighLightSearch() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.matchQuery("all", "安交路"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name").requireFieldMatch(false);
        request.source().highlighter(highlightBuilder);
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        handleResponse(response);
    }

    @Test
    void testShowHighLightSearchRequest() throws IOException {
        SearchRequest request = new SearchRequest(indexName);
        request.source().query(QueryBuilders.matchQuery("all", "安交路"));
        request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        SearchHits searchHits = response.getHits();
        TotalHits totalHits = searchHits.getTotalHits();
        SearchHit[] hits = searchHits.getHits();

        Arrays.stream(hits).forEach(hit -> {
            HotelDoc hotelDoc = JSON.parseObject(hit.getSourceAsString(), HotelDoc.class);
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();

            if (!CollectionUtils.isEmpty(highlightFieldMap)) {
                HighlightField highlightField = highlightFieldMap.get("name");
                if (highlightField == null) return;

                String name = highlightField.fragments()[0].string();
                hotelDoc.setName(name);
            }
            System.out.println(hotelDoc);
        });

    }
}