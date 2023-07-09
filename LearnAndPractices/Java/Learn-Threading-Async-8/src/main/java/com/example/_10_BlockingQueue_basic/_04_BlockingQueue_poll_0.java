package com.example._10_BlockingQueue_basic;



import com.example.ThreadUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class _04_BlockingQueue_poll_0 {
    public static void main(String[] args) {
        BlockingQueue<String> shaobingQueue = new LinkedBlockingQueue<>(3);

        Thread xiaoBai = new Thread(() -> {
            ThreadUtils.printTimeAndThread("Xiaobai, pack up your things and prepare to open");
            ThreadUtils.printTimeAndThread("Xiaobai got a call and ran home");
        });

        Thread roadPeopleA = new Thread(() -> {
            ThreadUtils.printTimeAndThread("Pedestrian A, come to buy biscuits");
            String shaobing = shaobingQueue.poll();
            if (shaobing == null) {
                ThreadUtils.printTimeAndThread("A passer-by did not buy sesame seed cakes");
            } else {
                ThreadUtils.printTimeAndThread("Passerby A bought sesame seed cakes: " + shaobing);
            }
        });

        xiaoBai.start();
        try {
            Thread.sleep(10);   // Wait for Xiaobai to clean up first, and then let passerby A play
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("main thread is interrupted" + e.getMessage());
        }
        roadPeopleA.start();
    }
}
