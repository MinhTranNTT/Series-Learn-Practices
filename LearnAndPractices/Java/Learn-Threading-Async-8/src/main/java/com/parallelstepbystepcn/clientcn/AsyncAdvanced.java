package com.parallelstepbystepcn.clientcn;

import com.parallelstepbystepcn.util.CommonUtilCn;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncAdvanced {
    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        // future 1
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int x = new Random().nextInt(3);
            CommonUtilCn.sleepSecond(x);
            CommonUtilCn.printThreadLog("Time1: " + x + " s");
            return x;
        });

        // future 2
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int y = new Random().nextInt(3);
            CommonUtilCn.sleepSecond(y);
            CommonUtilCn.printThreadLog("Time2: " + y + " s");
            return y;
        });

        CompletableFuture<Integer> future = future1.applyToEither(future2, (result -> {
            CommonUtilCn.printThreadLog("applyToEither:" + result + " delay");
            return result;
        }));

        CommonUtilCn.sleepSecond(4);
        Integer ret = future.get();
        CommonUtilCn.printThreadLog("ret = " + ret);
    }

    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        // future 1
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int x = new Random().nextInt(5);
            CommonUtilCn.sleepSecond(x);
            CommonUtilCn.printThreadLog("Time1: " + x + " s");
            return x;
        });

        // future 2
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int y = new Random().nextInt(5);
            CommonUtilCn.sleepSecond(y);
            CommonUtilCn.printThreadLog("Time2: " + y + " s");
            return y;
        });

        CompletableFuture<Void> future = future1.acceptEither(future2, (result -> {
            CommonUtilCn.printThreadLog("acceptEither:" + result + " s delay");
        }));

        CommonUtilCn.sleepSecond(4);
        CommonUtilCn.printThreadLog("End thread");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // future 1
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int x = new Random().nextInt(5);
            CommonUtilCn.sleepSecond(x);
            CommonUtilCn.printThreadLog("Time1: " + x + " s");
            return x;
        });

        // future 2
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int y = new Random().nextInt(5);
            CommonUtilCn.sleepSecond(y);
            CommonUtilCn.printThreadLog("Time2: " + y + " s");
            return y;
        });

        CompletableFuture<Void> future = future1.runAfterEither(future2, () -> {
            CommonUtilCn.printThreadLog("runAfterEither:" + " delay");
        });

        CommonUtilCn.sleepSecond(4);
        CommonUtilCn.printThreadLog("End thread");
    }
}
