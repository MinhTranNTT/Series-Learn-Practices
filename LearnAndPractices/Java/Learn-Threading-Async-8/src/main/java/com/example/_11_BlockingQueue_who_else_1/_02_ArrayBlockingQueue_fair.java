package com.example._11_BlockingQueue_who_else_1;



import com.example.ThreadUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class _02_ArrayBlockingQueue_fair {
    private static BlockingQueue<String> shaobingQueue = new ArrayBlockingQueue<>(3,true);

    public static void main(String[] args) {
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
                String tag = shaobing == null ? "Grass, dare not lift Haotianzong's stall! " : "I bought biscuits";
                ThreadUtils.printTimeAndThread(tag);
            } catch (InterruptedException e) {
                ThreadUtils.printTimeAndThread("interrupted" + e.getMessage());
            }
        }, "Li Si").start();

        shaobingQueue.offer("Sesame Biscuits");
    }
}
