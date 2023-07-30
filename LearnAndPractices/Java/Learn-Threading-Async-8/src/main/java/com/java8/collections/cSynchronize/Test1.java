package com.java8.collections.cSynchronize;

import java.util.concurrent.ConcurrentHashMap;

public class Test1 {
    public static void main(String[] args) {
        // Khởi tạo một ConcurrentHashMap đơn giản
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");

        // Stream trên map và cập nhật thông tin
        map.forEach((key, value) -> {
            String newValue = value + " Updated";
            map.put(key, newValue);
        });

        // In ra Map sau khi cập nhật
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
