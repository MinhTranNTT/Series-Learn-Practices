package com.practices.countdownlatchh;

import java.util.concurrent.CountDownLatch;

public class TestCountDown {
    public final static int studentsNum = 10;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(studentsNum);
        for (int i = 0; i < studentsNum; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t after self study, leave classroom.");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // main thread will wait until all threads complete, countdown reduces to 0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t The monitor locked the door and left the classroom.");
    }
}
