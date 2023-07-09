package com.example._04_CompletableFuture_advance;

import com.example.ThreadUtils;


import java.util.concurrent.CompletableFuture;

public class _01_thenApply {
    public static void main(String[] args) {
        ThreadUtils.printTimeAndThread("Xiaobai is ready");
        ThreadUtils.printTimeAndThread("Xiaobai checkout, request invoice");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("The waiter collects 500 yuan");
            ThreadUtils.sleepMillis(100);
            return "500";
        }).thenApplyAsync(money -> {
            ThreadUtils.printTimeAndThread(String.format("The waiter invoices the denomination %s Yuan", money));
            ThreadUtils.sleepMillis(200);
            return String.format("%sYuan invoice", money);
        });

        ThreadUtils.printTimeAndThread("Xiaobai received a call from a friend and wanted to play games together");

        ThreadUtils.printTimeAndThread(String.format("Xiaobai got it%s，ready to go home", invoice.join()));
    }


    private static void one() {
        ThreadUtils.printTimeAndThread("Xiaobai is ready");
        ThreadUtils.printTimeAndThread("Xiaobai checkout, request invoice");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("The waiter collects 500 yuan");
            ThreadUtils.sleepMillis(100);
            ThreadUtils.printTimeAndThread("The waiter issues an invoice with a denomination of 500 yuan");
            ThreadUtils.sleepMillis(200);
            return "500Yuan invoice";
        });

        ThreadUtils.printTimeAndThread("Xiaobai received a call from a friend and wanted to play games together");

        ThreadUtils.printTimeAndThread(String.format("Xiaobai got it%s，ready to go home", invoice.join()));
    }


    private static void two() {
        ThreadUtils.printTimeAndThread("Xiaobai is ready");
        ThreadUtils.printTimeAndThread("Xiaobai checkout, request invoice");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.printTimeAndThread("The waiter collects 500 yuan");
            ThreadUtils.sleepMillis(100);

            CompletableFuture<String> waiter2 = CompletableFuture.supplyAsync(() -> {
                ThreadUtils.printTimeAndThread("The waiter issues an invoice with a denomination of 500 yuan");
                ThreadUtils.sleepMillis(200);
                return "500Yuan invoice";
            });

            return waiter2.join();
        });

        ThreadUtils.printTimeAndThread("Xiaobai received a call from a friend and wanted to play games together");

        ThreadUtils.printTimeAndThread(String.format("Xiaobai got it%s，ready to go home", invoice.join()));
    }
}
