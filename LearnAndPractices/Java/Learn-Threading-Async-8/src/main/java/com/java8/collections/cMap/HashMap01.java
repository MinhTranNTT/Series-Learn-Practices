package com.java8.collections.cMap;

import java.util.HashMap;
import java.util.Map;

public class HashMap01 {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(9, "NINE");
        map.put(1, "ONE");
        map.put(2, null);

        map.putIfAbsent(2, "TWO");
        map.putIfAbsent(3, "TWO2");
        System.out.println(map);


    }

//    public static void main(String[] args) {
//        Map<Integer,String> map = new HashMap<>();
//        map.put(9, "NINE");
//        map.put(1, "ONE");
//        map.put(10, "TEN");
//        map.put(10, "TENA");
//        map.put(12,null );
//        map.put(null,"A");
//
//        System.out.println(map);
//    }
}
