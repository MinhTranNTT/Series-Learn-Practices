package com.practices.colllections.trying;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Trying03 {
    public static void main(String[] args) {
        List<String> data = getData();

        // for (String name : data) {
        //     String s = name.toUpperCase();
        //     System.out.println(s);
        // }
        //
        // for (String name : data) {
        //     String s = name.toLowerCase();
        //     System.out.println(s);
        // }

        handlerCase(data,String::toUpperCase,"ToUpperCase");
        handlerCase(data,String::toLowerCase,"ToUpperCase");

    }

    private static void handlerCase(List<String> data,
                                    Function<String, String> func,
                                    String processType) {
        System.out.println("Process type name: " + processType);
        for (String name : data) {
            String apply = func.apply(name);
            System.out.println(apply);
        }
    }

    private static List<String> getData() {
        return Arrays.asList("Tom","Jerry","Harry");
    }
}
