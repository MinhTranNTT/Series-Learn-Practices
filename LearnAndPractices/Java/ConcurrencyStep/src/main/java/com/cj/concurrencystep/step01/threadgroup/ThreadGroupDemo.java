package com.cj.concurrencystep.step01.threadgroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadGroupDemo {
    // countDownLatch not decrease 0, then application not exist
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static List<Thread> DbConnThread() {
        ThreadGroup dbConnThreadGroup = new ThreadGroup("Database connection thread group");
        List<Thread> dbConnThreadList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(dbConnThreadGroup, new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread name: " + Thread.currentThread().getName()
                            + ", Thread group: " + Thread.currentThread().getThreadGroup().getName());
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "db-conn-thread-" + i);
            dbConnThreadList.add(t);
        }
        return dbConnThreadList;
    }

    public static List<Thread> httpReqThread() {
        ThreadGroup httpReqThreadGroup = new ThreadGroup("Third-party http request thread group");
        List<Thread> httpReqThreadList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(httpReqThreadGroup, new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread name: " + Thread.currentThread().getName()
                            + ", Thread group: " + Thread.currentThread().getThreadGroup().getName());
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "http-req-thread-" + i);
            httpReqThreadList.add(t);
        }
        return httpReqThreadList;
    }

    public static void startThread(List<Thread> threadList) {
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> dbConnThreadList = DbConnThread();
        List<Thread> httpReqThreadList = httpReqThread();
        startThread(dbConnThreadList);
        startThread(httpReqThreadList);
        Thread.sleep(2000);
    }
}
