package com.example._11_BlockingQueue_who_else_1;



import com.example.ThreadUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class _01_LinkedBlockingQueue_unfair {

    public static void main(String[] args) {
        BlockingQueue<String> shaobingQueue = new LinkedBlockingQueue<>(3);

        new Thread(() -> {
            try {
                ThreadUtils.printTimeAndThread("come to buy biscuits");
                String shaobing = shaobingQueue.poll(1, TimeUnit.SECONDS);
                String tag = shaobing == null ? "goodbye, no more" : "I bought biscuits";
                ThreadUtils.printTimeAndThread(tag);
            } catch (InterruptedException e) {
                ThreadUtils.printTimeAndThread("interrupted" + e.getMessage());
            }
        }, "Zhang San").start();

        ThreadUtils.sleepMillis(100);     // Simulate that Zhang San arrives first

        new Thread(() -> {
            try {
                ThreadUtils.printTimeAndThread("come to buy biscuits");
                String shaobing = shaobingQueue.poll(1, TimeUnit.SECONDS);
                String tag = shaobing == null ? "grass! " : "I bought biscuits";
                ThreadUtils.printTimeAndThread(tag);
            } catch (InterruptedException e) {
                ThreadUtils.printTimeAndThread("interrupted" + e.getMessage());
            }
        }, "Li Si").start();

        shaobingQueue.offer("Sesame Biscuits");
    }
}
