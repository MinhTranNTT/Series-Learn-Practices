package com.example._07_CompletableFuture_performance;



import com.example.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _06_thenRunAsync_threadReuse {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>());

        CompletableFuture.runAsync(() -> ThreadUtils.printTimeAndThread("A"), executor)
                .thenRunAsync(() -> ThreadUtils.printTimeAndThread("B"), executor)
                .join();

    }
}
