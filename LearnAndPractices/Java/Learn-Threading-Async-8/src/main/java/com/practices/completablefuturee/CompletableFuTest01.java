package com.practices.completablefuturee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuTest01 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        /**
         * runAsync has no return value
         */
        // CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
        //     System.out.println("current thread " + Thread.currentThread().getId());
        //     int i = 10 / 2;
        //     System.out.println("operation result:" + i);
        // }, executor);

        /**
         * supplyAsync has a return value
         * whenComplete can sense exceptions and results, but cannot return a value
         * Exceptionally can sense exceptions, cannot sense results, and can give return values. Equivalent to returning this value if an exception occurs
         */
        // CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
        //     System.out.println("current thread" + Thread.currentThread().getId());
        //     int i = 10 / 0;
        //     System.out.println("operation result:" + i);
        //     return i;
        // }, executor).whenComplete((res,exception)->{
        //     //Although whenComplete can get exception information, it cannot modify the return value.
        //     System.out.println("The async task completed successfully... the result is: "+res+" ;The exception is: "+exception);
        // }).exceptionally(throwable -> {
        //     // Exceptionally can sense exceptions and return a default value, which is equivalent to returning this value if an exception occurs.
        //     int i = 10;
        //     System.out.println("Exception: " + i);
        //     return i;
        // });

        /**
         * supplyAsync has a return value
         * handle can get the return result, exception information, and modify the return value
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("current thread " + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("operation result: " + i);
            return i;
        }, executor).handle((res,exception)->{
            if(exception!=null){
                return 0;
            }else {
                return res * 2;
            }
        });


    }




}
