package com.dragon.primary.service.impl;

import com.dragon.primary.service.IHotelService;
import com.dragon.springbootes.SpringBootEsApplication;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;

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
            System.out.println(json);
        });

        System.out.println("total: " + total);
    }
}