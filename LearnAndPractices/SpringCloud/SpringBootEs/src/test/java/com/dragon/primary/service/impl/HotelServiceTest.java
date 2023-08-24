package com.dragon.primary.service.impl;

import com.alibaba.fastjson.JSON;
import com.dragon.primary.constants.HotelConstants;
import com.dragon.primary.pojo.Hotel;
import com.dragon.primary.pojo.HotelDoc;
import com.dragon.primary.service.IHotelService;
import com.dragon.springbootes.SpringBootEsApplication;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static com.dragon.primary.constants.HotelConstants.MAPPING_TEMPLATE;
import static com.dragon.primary.constants.HotelConstants.indexName;

@SpringBootTest(classes = SpringBootEsApplication.class)
class HotelServiceTest {

    @Autowired private IHotelService hotelService;
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("localhost:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }

    @Test
    void init() {
        System.out.println(client);
    }

    @Test
    void testCreateHotelIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        this.client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void testExistHotelIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        boolean exists = this.client.indices().exists(request, RequestOptions.DEFAULT);
        System.err.println(exists ? "Exist Index" : "Not Exist Index");
    }

    @Test
    void testDeleteHotelIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        this.client.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testIndexDocument() throws IOException {
        Hotel hotel = hotelService.getById(36934L);
        HotelDoc hotelDoc = HotelDoc.fromHotelDoc(hotel);

        IndexRequest request = new IndexRequest(indexName).id(hotelDoc.getId().toString());
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        this.client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    void testGetRequestDocument() throws IOException {
        GetRequest request = new GetRequest(indexName, "36934");
        GetResponse response = this.client.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        System.out.println(json);
    }

    @Test
    void testObjGetRequestDocument() throws IOException {
        GetRequest request = new GetRequest(indexName, "36934");
        GetResponse response = this.client.get(request, RequestOptions.DEFAULT);

        HotelDoc hotelDoc = JSON.parseObject(response.getSourceAsString(), HotelDoc.class);
        System.out.println(hotelDoc);
    }

    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, "36934");
        request.doc(
                "price", "123",
                "starName", "MinhTran"
        );
        this.client.update(request, RequestOptions.DEFAULT);
    }

    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(indexName,"36934");
        this.client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void getHotelDocById() {
        Hotel hotel = hotelService.getById(36934L);
        HotelDoc hotelDoc = HotelDoc.fromHotelDoc(hotel);
        System.out.println(hotelDoc);
    }

    @Test
    void testBulkDocument() throws IOException {
        List<Hotel> list = hotelService.list();
        BulkRequest request = new BulkRequest(indexName);

        list.forEach(hotel -> {
            HotelDoc hotelDoc = HotelDoc.fromHotelDoc(hotel);
            request.add(new IndexRequest(indexName)
                    .id(hotelDoc.getId().toString())
                    .source(JSON.toJSONString(hotelDoc), XContentType.JSON)
            );
        });

        this.client.bulk(request, RequestOptions.DEFAULT);
    }
}