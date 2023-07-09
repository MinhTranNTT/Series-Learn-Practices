package com.example._05_CompletableFuture_expand;



import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;

public class _02_thenCompose {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook cooking");
            ThreadUtils.sleepMillis(200);
            return "Tomato Scrambled Eggs";
        }).thenCompose(dish -> {
            ThreadUtils.printTimeAndThread("Waiter A was going to cook, but was called away by the leader, and the cook was handed over to waiter B");

            return CompletableFuture.supplyAsync(() -> {
                ThreadUtils.printTimeAndThread("Waiter B is cooking");
                ThreadUtils.sleepMillis(100);
                return dish + " + rice";
            });
        });

        ThreadUtils.printTimeAndThread(cf1.join()+"ok, dinner");
    }
}
