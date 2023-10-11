package com.cj.concurrencystep.step02.close;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorShutDownDemo2 {
    public static void testShutDown() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[testShutDown] begin");
                    for(int i=0;i<10;i++){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally {
                    System.out.println("[testShutDown] end");
                }
            }
        });
        Thread.sleep(2000);
        executorService.shutdown();
        System.out.println("[testShutDown] shutdown");
    }

    public static void testShutDownNow() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[testShutDownNow] begin");
                    for(int i=0;i<10;i++){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }finally {
                    System.out.println("[testShutDownNow] end");
                }
            }
        });
        Thread.sleep(2000);
        executorService.shutdownNow();
        System.out.println("[testShutDownNow] shutdownNow");
    }

    // Through experiments, we found that calling shutdown directly will not immediately end the task of the thread in the thread pool,
    // but will wait for the thread to complete the task.

    // Thread.sleep(2000); if cost in pool thread > Thread.sleep(xxx) ==> sleep interrupted
    public static void main(String[] args) throws InterruptedException {
//        testShutDown();
        testShutDownNow();
    }
}
