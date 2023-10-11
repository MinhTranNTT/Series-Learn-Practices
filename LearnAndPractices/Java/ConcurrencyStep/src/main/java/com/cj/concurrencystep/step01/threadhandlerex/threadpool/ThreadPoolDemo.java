package com.cj.concurrencystep.step01.threadhandlerex.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static ExecutorService executors = Executors.newFixedThreadPool(1);
    public static void main(String[] args) throws InterruptedException {
        executors.submit(() -> {
            System.out.println("test");
        });
        Thread.sleep(200);
        Thread t1 = new Thread(() -> {
            System.out.println("test-2");
        });
        t1.start();
        t1.stop();      // terminate    not recommend; removed from java 11 and higher
        t1.suspend();   // suspend      not recommend; removed from java 11 and higher
        t1.resume();    // continue again
    }
}
