package com.neet.neetdesign.validator;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClientAnimals {
    public static void main(String[] args) {
        Animal dog = new Animal(1,"dog",5);
        Animal cat = new Animal(2,"cat",2);
        Animal bird = new Animal(3,"bird",6);
        List<Animal> list = new ArrayList<>();
        list.add(dog);
        list.add(cat);
        list.add(bird);

        // {"age":5,"id":1,"name":"dog"}
        // {"age":2,"id":2,"name":"cat"}
        // {"age":6,"id":3,"name":"bird"}
        for (int i = 0; i < list.size(); i++) {
            JSONObject obj = JSONObject.from(list.get(i));
            System.out.println(obj.toJSONString());
        }

        // {"id":"1","name":"dog","age":"5"}
        // {"id":"2","name":"cat","age":"2"}
        // {"id":"3","name":"bird","age":"6"}
        for (Animal animal : list) {
            System.out.println(objectToJson(animal));
        }

        System.out.println("=========================");
        Gson gson = new Gson();
        for (Animal animal : list) {
            String json = gson.toJson(animal);
            System.out.println(json);
        }

        System.out.println("========================= ObjectMapper");
        // ObjectMapper objectMapper = new ObjectMapper();
        // for (Animal animal : list) {
        //     try {
        //         String json = objectMapper.writeValueAsString(animal);
        //         System.out.println(json);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
    }

    public static String objectToJson(Object obj) {
        StringBuilder json = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                json.append("\"").append(field.getName()).append("\":\"").append(field.get(obj)).append("\",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        json.deleteCharAt(json.length() - 1); // Xóa dấu phẩy cuối cùng
        json.append("}");
        return json.toString();
    }
}
