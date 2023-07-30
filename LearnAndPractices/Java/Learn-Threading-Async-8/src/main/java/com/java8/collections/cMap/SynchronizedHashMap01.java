package com.java8.collections.cMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedHashMap01 {
    public static void main(String[] args) {
        // create a synchronized HashMap
        Map<String,Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        // Create multiple threads to access and modify the synchronized map
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                synchronizedMap.put("Key" + i, i);
            }
        };

        // Create and start multiple threads
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        // Wait for the threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the synchronized map
        System.out.println(synchronizedMap);
    }
}
