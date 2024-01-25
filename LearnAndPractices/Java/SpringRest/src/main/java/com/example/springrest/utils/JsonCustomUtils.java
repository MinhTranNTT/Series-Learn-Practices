package com.example.springrest.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonCustomUtils {


    public static <T> List<T> str2list(String json, Class<T> clazz) {
        Type type = new TypeToken<List<T>>() {}.getType();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, type);
    }

    // order properties
    public static <T> List<T> str2list2(String json, Class<T> clazz) {
        Type type = new TypeToken<List<LinkedHashMap<String, Object>>>() {}.getType();
        Gson gson = new GsonBuilder().create();
        List<LinkedHashMap<String, Object>> list = gson.fromJson(json, type);

        // Convert LinkedHashMap to your object
        List<T> resultList = new ArrayList<>();
        for (LinkedHashMap<String, Object> map : list) {
            T obj = gson.fromJson(gson.toJsonTree(map), clazz);
            resultList.add(obj);
        }

        return resultList;
    }

}
