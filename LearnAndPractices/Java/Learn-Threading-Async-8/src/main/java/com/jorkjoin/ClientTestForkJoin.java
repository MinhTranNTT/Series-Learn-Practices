package com.jorkjoin;

import java.util.concurrent.ForkJoinPool;

public class ClientTestForkJoin {
    public static void main(String[] args) {
        int sumBegin = 0, sumEnd = 10000000;
        computeByForkJoin(sumBegin, sumEnd);
        computeBySingleThread(sumBegin, sumEnd);
    }

    //    ======
    //    ForkJoin Task splitting: 131071
    //    ForkJoin Calculation results: 49999995000000
    //    ForkJoin Calculation time: 269
    private static void computeByForkJoin(int sumBegin, int sumEnd) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);
        long forkJoinStartTime = System.nanoTime();
        RecursiveSumTask theKingRecursiveSumTask = new RecursiveSumTask(sumBegin, sumEnd, 100);
        long forkJoinResult = (long) forkJoinPool.invoke(theKingRecursiveSumTask);
        System.out.println("======");
        System.out.println("ForkJoin Task splitting: " + RecursiveSumTask.getTaskCount());
        System.out.println("ForkJoin Calculation results: " + forkJoinResult);
        System.out.println("ForkJoin Calculation time: " + (System.nanoTime() - forkJoinStartTime) / 1000000);
    }

    //    ======
    //    Single thread calculation results: 49999995000000
    //    Single thread calculation time: 31
    private static void computeBySingleThread(int sumBegin, int sumEnd) {
        long computeResult = 0L;
        long startTime = System.nanoTime();
        for (int i = sumBegin; i < sumEnd; i++) {
            computeResult += i;
        }
        System.out.println("======");
        System.out.println("Single thread calculation results: " + computeResult);
        System.out.println("Single thread calculation time: " + (System.nanoTime() - startTime) / 1000000);
    }

}
