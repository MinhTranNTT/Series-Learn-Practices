package com.neet.neetdesign.miscellaneous.datalist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");

        String joinStr1 = list.stream().collect(Collectors.joining(","));
        String joinStr2 = String.join(",", list);
        System.out.println(joinStr1);
        System.out.println(joinStr2);

        String[] arrStr = joinStr1.split(",");
        System.out.println(Arrays.toString(arrStr)); // [test1, test2, test3]

        List<String> lstStr = Arrays.asList(joinStr1.split(","));
        System.out.println(lstStr); // [test1, test2, test3]

        String strLine = "aa bb cc";
        char[] charArray = strLine.toCharArray();
        System.out.println(Arrays.toString(charArray)); // [a, a,  , b, b,  , c, c]
    }
}
