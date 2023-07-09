package com.example._09_BlockingQueue_start;



import com.example.ThreadUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class _06_LinkedBlockingQueue_put {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(1);

        try {
            blockingQueue.put("one");
            ThreadUtils.printTimeAndThread("oneput it in");

            blockingQueue.put("two");
            ThreadUtils.printTimeAndThread("twoput it in");

        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("Fetching elements is interrupted");
        }
    }
}
