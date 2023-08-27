package com.dragon.it.common;

import com.alibaba.fastjson.JSON;
import com.dragon.it.pojo.HotelDoc;
import com.dragon.it.pojo.PageResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
