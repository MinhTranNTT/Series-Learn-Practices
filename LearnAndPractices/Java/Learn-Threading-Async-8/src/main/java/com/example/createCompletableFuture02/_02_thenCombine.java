package com.example.createCompletableFuture02;

import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;

public class _02_thenCombine {
    public static void main(String[] args) {
        ThreadUtils.printTimeAndThread("Nobita enters the restaurant");
        ThreadUtils.printTimeAndThread("Nobita ordered tomato scrambled eggs + a bowl of rice");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("cook cooking");
            ThreadUtils.sleepMillis(200);
            return "Tomato Scrambled Eggs";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("waiter steaming rice");
            ThreadUtils.sleepMillis(300);
            return "rice";
        }), (dish, rice) -> {
            ThreadUtils.printTimeAndThread("waiter cooking");
            ThreadUtils.sleepMillis(100);
            return String.format("%s + %s alright", dish, rice);
        });

        ThreadUtils.printTimeAndThread("Nobita is playing the king");
        ThreadUtils.printTimeAndThread(String.format("%s ,Nobita eat", cf1.join()));
    }
}
