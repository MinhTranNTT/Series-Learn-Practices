package com.example._09_BlockingQueue_start;



import com.example.ThreadUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class _05_LinkedBlockingQueue_take {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);

        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            ThreadUtils.printTimeAndThread("Fetching elements is interrupted");
        }
    }
}
