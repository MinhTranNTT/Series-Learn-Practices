package com.dragon.it.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.dragon.it.constants.HotelConstants.HOST_ELASTIC;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient client() {
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create(HOST_ELASTIC)
        ));
    }
}
