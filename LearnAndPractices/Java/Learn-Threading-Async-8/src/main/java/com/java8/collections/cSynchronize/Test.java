package com.java8.collections.cSynchronize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        // Khởi tạo một List và một Map đơn giản
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");

        // Stream trên List và cập nhật thông tin trong Map
        numbers.parallelStream().forEach(number -> {
            String newValue = map.get(number) + " Updated - " + number;
            map.put(number, newValue);
        });

        // In ra Map sau khi cập nhật
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
