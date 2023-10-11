package com.cj.concurrencystep.step02.close;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorShutDownDemo {
    public static void testShutDown() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[testShutDown] begin");
                    while (true) {
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
                    while (true) {
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

    public static void main(String[] args) throws InterruptedException {
        testShutDown();
        testShutDownNow();

        /*The program doesn't shut down completely because in both cases you are doing an infinite loop (while (true))
        in the thread of execution by the ExecutorService.
        Therefore, when you call shutdown() or shutdownNow(),
        it only tries to end the tasks running in the pool, but the infinite loop is still running.*/
    }
}
