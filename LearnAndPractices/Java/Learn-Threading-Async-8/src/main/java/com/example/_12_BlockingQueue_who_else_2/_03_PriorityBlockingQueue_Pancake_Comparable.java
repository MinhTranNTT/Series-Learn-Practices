package com.example._12_BlockingQueue_who_else_2;/*
package org.ph.share._12_BlockingQueue_who_else_2;



import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class _03_PriorityBlockingQueue_Pancake_Comparable {

    public static void main(String[] args) {
        BlockingQueue<Pancake> blockingQueue = new PriorityBlockingQueue<>();

        Thread xiaobai = new Thread(() -> {
            blockingQueue.offer(new Pancake(2));
            ThreadUtils.printTimeAndThread("做好第1个烧饼");

            blockingQueue.offer(new Pancake(0));
            ThreadUtils.printTimeAndThread("做好第2个烧饼");

            blockingQueue.offer(new Pancake(1));
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

    private record Pancake(
*
             * 美味程度
             *  0 --> 好吃
             *  1 --> 一般
             *  2 --> 难吃


            Integer flavor
    ) implements Comparable<Pancake> {
        @Override
        public int compareTo(Pancake o) {
            return this.flavor.compareTo(o.flavor);
        }
    }
}

*/
