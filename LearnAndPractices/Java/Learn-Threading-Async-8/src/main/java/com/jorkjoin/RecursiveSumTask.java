package com.jorkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class RecursiveSumTask extends RecursiveTask {
    private static final AtomicInteger taskCount = new AtomicInteger();
    private final int sumBegin;
    private final int sumEnd;
    // Task splitting threshold, when the task size is greater than this value, split it
    private final int threshold;

    public RecursiveSumTask(int sumBegin, int sumEnd, int threshold) {
        this.sumBegin = sumBegin;
        this.sumEnd = sumEnd;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if ((sumEnd - sumBegin) > threshold) {
            // The difference between the two numbers is greater than the threshold, split the task
            RecursiveSumTask subTask1 = new RecursiveSumTask(sumBegin, (sumBegin + sumEnd) / 2, threshold);
            RecursiveSumTask subTask2 = new RecursiveSumTask((sumBegin + sumEnd) / 2, sumEnd, threshold);
            subTask1.fork();
            subTask2.fork();
            taskCount.incrementAndGet();
            return (Long) subTask1.join() + (Long) subTask2.join();
        }
        // Directly execute the result
        long result = 0L;
        for (int i = sumBegin; i < sumEnd; i++) {
            result += i;
        }
        return result;
    }

    public static AtomicInteger getTaskCount() {
        return taskCount;
    }
}
