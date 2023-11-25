package com.practices.colllections.trying;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.LongStream;

public class Trying04 {

    private static int nation;

    public static void main(String[] args) {
        long[] longs = LongStream.of(10, 11, 54, 49, 5, 94, 105, 88)
                .filter(data -> data > 10)
                .map(data -> data * 2)
                .distinct()
                .sorted()
                .toArray();
        System.out.println(Arrays.toString(longs));

        Integer age = new Integer(10);
        System.out.println(age);

        boolean flag = true;
        String name = "hihi";
        short a = 10;
        nation = (int) a;

        nation = 10;

        Long longValue = 100L;
        System.out.println(longValue==100);
        System.out.println(Objects.equals(longValue, 100L));
    }
}
