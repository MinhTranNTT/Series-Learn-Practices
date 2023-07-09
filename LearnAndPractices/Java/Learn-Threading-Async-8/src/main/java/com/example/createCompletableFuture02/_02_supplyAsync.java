package com.example.createCompletableFuture02;

import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;

public class _02_supplyAsync {
    public static void main2(String[] args) {
        ThreadUtils.printTimeAndThread("Nobita enters the restaurant");
        ThreadUtils.printTimeAndThread("Nobita ordered tomato scrambled eggs + a bowl of rice");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook tomato scramble eggs");
            ThreadUtils.sleepMillis(200);
            ThreadUtils.printTimeAndThread("cook a bowl of rice");
            ThreadUtils.sleepMillis(100);
            return "Tomato scrambled eggs + rice is ready";
        });

        ThreadUtils.printTimeAndThread("Nobita is playing the LOL");
        ThreadUtils.printTimeAndThread(String.format("%s ,Nobita eat", future.join()));
    }

    public static void main(String[] args) {
        ThreadUtils.printTimeAndThread("Nobita enters the restaurant");
        ThreadUtils.printTimeAndThread("Nobita ordered tomato scrambled eggs + a bowl of rice");

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook tomato scramble eggs");
            ThreadUtils.sleepMillis(200);
            return "DONE cook tomato scramble eggs";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook a bowl of rice");
            ThreadUtils.sleepMillis(100);
            return "DONE cook a bowl of rice";
        });

        ThreadUtils.printTimeAndThread("Nobita is playing the LOL");
        ThreadUtils.printTimeAndThread(String.format("%s ,Nobita eat 1: ", future1.join()));
        ThreadUtils.printTimeAndThread(String.format("%s ,Nobita eat 2: ", future2.join()));
    }
}
