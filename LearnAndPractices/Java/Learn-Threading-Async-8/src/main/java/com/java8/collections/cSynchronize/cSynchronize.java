package com.java8.collections.cSynchronize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class cSynchronize {
    public static void main(String[] args) {
        Map<String, Object> map = getMapDefault();
        List<TimeRate> list = getListRate();

        List<CompletableFuture<String>> collect = list.stream().map(timeRate -> CompletableFuture.supplyAsync(() -> {
            return changeMap(timeRate, map);
//            return changeMapSynchronize(timeRate, map);
        })).collect(Collectors.toList());

        CompletableFuture<Void> resultCollect = CompletableFuture.allOf(collect.toArray(new CompletableFuture[0]));

        List<String> listString = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(listString);

    }

    private static String changeMap(TimeRate timeRate, Map<String, Object> map) {
        map.put("limit", timeRate.getLimit());
        map.put("offset", timeRate.getOffset());
        System.out.println("limit: " + map.get("limit") + "       -        offset: " + map.get("offset"));
        return "offset - "+map.get("offset");
    }

    private static String changeMapSynchronize(TimeRate timeRate, Map<String, Object> map) {
        synchronized (map) {
            map.put("limit", timeRate.getLimit());
            map.put("offset", timeRate.getOffset());
            System.out.println("limit: " + map.get("limit") + "       -        offset: " + map.get("offset"));
        }
//        System.out.println("limit: " + map.get("limit") + "       -        offset: " + map.get("offset"));
        return "offset - "+map.get("offset");
    }

    public static List<TimeRate> getListRate() {
        List<TimeRate> list = new ArrayList<>();
        list.add(new TimeRate(10, 20));
        list.add(new TimeRate(10, 30));
        list.add(new TimeRate(10, 40));
        return  list;
    }

    public static Map<String, Object> getMapDefault() {
        Map<String, Object> map = new HashMap<>();
        map.put("limit", 0);
        map.put("offset", 0);
        return map;
    }

    private static String changeMap2(TimeRate timeRate, Map<String, Object> map) {
        Map<String, Object> localMap = new HashMap<>(map); // Create a copy of the original map
        localMap.put("limit", timeRate.getLimit());
        localMap.put("offset", timeRate.getOffset());
        System.out.println("limit: " + localMap.get("limit") + "       -        offset: " + localMap.get("offset"));
        return "offset - " + localMap.get("offset");
    }
}
