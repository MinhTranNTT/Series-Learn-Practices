package com.example._11_BlockingQueue_who_else_1;



import com.example.ThreadUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class _04_SynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> shaobingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                ThreadUtils.printTimeAndThread("Ready to start making biscuits");
                shaobingQueue.put("Sesame Biscuits No. 1");
                ThreadUtils.printTimeAndThread("The first biscuit was sold");

                ThreadUtils.sleepMillis(2000);    // rest for two seconds before continuing

                shaobingQueue.put("Sesame Biscuits No. 2");
                ThreadUtils.printTimeAndThread("Sold out the second biscuits");
            } catch (InterruptedException e) {
                ThreadUtils.printTimeAndThread("interrupted" + e.getMessage());
            }
        }, "noob").start();

        new Thread(() -> {
            try {
                ThreadUtils.sleepMillis(1000);    // Not hungry yet, wait a second

                ThreadUtils.printTimeAndThread("bought" + shaobingQueue.take());
                ThreadUtils.printTimeAndThread("Finished in an instant, continue to buy");
                ThreadUtils.printTimeAndThread("bought" + shaobingQueue.take());
            } catch (InterruptedException e) {
                ThreadUtils.printTimeAndThread("interrupted" + e.getMessage());
            }
        }, "Zhang San").start();

    }
}
