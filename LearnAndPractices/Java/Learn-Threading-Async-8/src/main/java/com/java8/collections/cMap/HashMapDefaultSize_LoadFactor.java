package com.java8.collections.cMap;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HashMapDefaultSize_LoadFactor {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        // Insert 16 elements
        for (int i = 0; i < 13; i++) {
            map.put("Key" + i, i);
        }

        // Print the size and capacity of the HashMap
        System.out.println("Size: " + map.size());
        System.out.println("Capacity: " + getCapacity2(map));

    }

    private static int getCapacity(HashMap<?, ?> map) {
        try {
            // Use reflection to access the loadFactor field of HashMap
            Field loadFactorField = HashMap.class.getDeclaredField("loadFactor");
            loadFactorField.setAccessible(true);
            float loadFactor = (float) loadFactorField.get(map);

            // Calculate the capacity based on the size and load factor
            int size = map.size();
            int capacity = (int) (size / loadFactor) + 1;

            return capacity;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private static int getCapacity2(HashMap<?, ?> map) {
        try {
            // Use reflection to access the capacity field of HashMap
            Field capacityField = HashMap.class.getDeclaredField("threshold");
            capacityField.setAccessible(true);
            int capacity = (int) capacityField.get(map);

            return capacity;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
