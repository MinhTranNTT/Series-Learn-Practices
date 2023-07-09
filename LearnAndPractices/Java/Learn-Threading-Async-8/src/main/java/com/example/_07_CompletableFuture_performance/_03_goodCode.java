package com.example._07_CompletableFuture_performance;



import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class _03_goodCode {
    public static void main(String[] args) {
        //-Djava.util.concurrent.ForkJoinPool.common.parallelism=8
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");

        ThreadUtils.printTimeAndThread("Xiaobai and his friends go to the restaurant to order");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] dishes = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> new Dish("vegetable" + i, 1))
                .map(dish -> CompletableFuture.runAsync(dish::make))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(dishes).join();

        ThreadUtils.printTimeAndThread("The dishes are ready, let's serve" + (System.currentTimeMillis() - startTime));

    }
}
