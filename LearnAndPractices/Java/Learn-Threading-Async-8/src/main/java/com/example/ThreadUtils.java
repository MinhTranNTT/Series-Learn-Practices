package com.example;

import java.util.StringJoiner;

public class ThreadUtils {
    // Utils sleep
    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Utils print
    public static void printTimeAndThread(String tag) {
        String result = new StringJoiner("\t|\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(String.format("%-40s",Thread.currentThread().getName()))
                .add(tag)
                .toString();
        System.out.println(result);
    }
}
