package com.practices.future;

import java.util.concurrent.*;

public class Future01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Minh Tran";
            }
        });
        System.out.println(future.isDone());
        System.out.println(future.get());
    }
}
