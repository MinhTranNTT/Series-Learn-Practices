package com.example._07_CompletableFuture_performance;



import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class _05_customThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        ThreadUtils.printTimeAndThread("Xiaobai and his friends go to the restaurant to order");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] dishes = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> new Dish("vegetable" + i, 1))
                .map(dish -> CompletableFuture.runAsync(dish::makeUseCPU, threadPool))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(dishes).join();

        threadPool.shutdown();

        ThreadUtils.printTimeAndThread("The dishes are ready, let's serve" + (System.currentTimeMillis() - startTime));

    }
}
