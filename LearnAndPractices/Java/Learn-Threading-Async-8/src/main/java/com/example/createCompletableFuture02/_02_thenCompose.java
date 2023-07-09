package com.example.createCompletableFuture02;

import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;

public class _02_thenCompose {
    public static void main2(String[] args) {
        ThreadUtils.printTimeAndThread("Nobita enters the restaurant");
        ThreadUtils.printTimeAndThread("Nobita ordered tomato scrambled eggs + a bowl of rice");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook cooking");
            ThreadUtils.sleepMillis(200);
            return "Tomato Scrambled Eggs";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("waiter cooking");
            ThreadUtils.sleepMillis(100);
            return dish + " + rice";
        }));

        ThreadUtils.printTimeAndThread("Nobita is playing the LOL");
        ThreadUtils.printTimeAndThread(String.format("%s Okay, Nobita, let's eat", cf1.join()));
    }

    public static void main(String[] args) {
        applyAsync();
    }

    private static void applyAsync() {
        ThreadUtils.printTimeAndThread("Nobita enters the restaurant");
        ThreadUtils.printTimeAndThread("Nobita ordered tomato scrambled eggs + a bowl of rice");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook cooking");
            ThreadUtils.sleepMillis(200);
            CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
                ThreadUtils.printTimeAndThread("waiter cooking");
                ThreadUtils.sleepMillis(100);
                return " + rice";
            });
            return "Tomato Scrambled Eggs" + race.join();
        });

        ThreadUtils.printTimeAndThread("Nobita is playing the LOL");
        ThreadUtils.printTimeAndThread(String.format("%s Okay, Nobita, let's eat", cf1.join()));
    }
}
