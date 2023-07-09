package com.example._12_BlockingQueue_who_else_2;/*
package org.ph.share._12_BlockingQueue_who_else_2;



import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class _04_PriorityBlockingQueue_Pancake_Comparable_enum {

    public static void main(String[] args) {
//        BlockingQueue<Pancake> blockingQueue = new PriorityBlockingQueue<>(
//                3,
//                (o1, o2) -> o1.flavor.compareTo(o2.flavor)
//        );
        BlockingQueue<Pancake> blockingQueue = new PriorityBlockingQueue<>(
                3,
                Comparator.comparing(Pancake::flavor)
        );

        Thread xiaobai = new Thread(() -> {
            blockingQueue.offer(new Pancake(Flavor.UNPALATABLE));
            ThreadUtils.printTimeAndThread("做好第1个烧饼");

            blockingQueue.offer(new Pancake(Flavor.DELICIOUS));
            ThreadUtils.printTimeAndThread("做好第2个烧饼");

            blockingQueue.offer(new Pancake(Flavor.EDIBLE));
            ThreadUtils.printTimeAndThread("做好第3个烧饼");
        }, "noob");

        xiaobai.start();
        try {
            xiaobai.join();     // 让小白顺利做完 3个烧饼
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("interrupted" + e.getMessage());
        }

        new Thread(() -> {
            ThreadUtils.printTimeAndThread("买到烧饼" + blockingQueue.poll());
        }, "Zhang San").start();

    }

    private record Pancake(Flavor flavor) {
    }

    private enum Flavor {
        DELICIOUS, // 好吃
        EDIBLE,     // 一般(能下口)
        UNPALATABLE // 难吃
    }

}
*/
