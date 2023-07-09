package com.example._04_CompletableFuture_advance;



import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;

public class _02_applyToEither {
    public static void main(String[] args) {
        ThreadUtils.printTimeAndThread("Zhang San walked out of the restaurant and came to the bus station");
        ThreadUtils.printTimeAndThread("Wait for bus 700 or 800 to arrive");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("700 bus is coming");
            ThreadUtils.sleepMillis(100);
            return "700 is here";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("800 bus is coming");
            ThreadUtils.sleepMillis(200);
            return "800 is here";
        }), firstComeBus -> firstComeBus);

        ThreadUtils.printTimeAndThread(String.format("%s,Xiaobai goes home by car", bus.join()));
    }
}
