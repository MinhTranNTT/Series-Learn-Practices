package com.parallelstepbystepcn.clientcn;

import com.parallelstepbystepcn.util.CommonUtilCn;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class RunAsyncDemo {
    public static void main1(String[] args) {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                CommonUtilCn.printThreadLog("Hello");
                CommonUtilCn.sleepSecond(3);
                CommonUtilCn.printThreadLog("End hello");
            }
        });

        CommonUtilCn.printThreadLog("here are not blocked,main continue");
        CommonUtilCn.sleepSecond(4);
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main2(String[] args) {
        CommonUtilCn.printThreadLog("main start");

        CompletableFuture.runAsync(() -> {
            CommonUtilCn.printThreadLog("Hello");
            CommonUtilCn.sleepSecond(3);
            CommonUtilCn.printThreadLog("End hello");
        });

        CommonUtilCn.printThreadLog("here are not blocked,main continue");
        CommonUtilCn.sleepSecond(4);
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main3(String[] args) {
        // read file async
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture.runAsync(() -> {
            String s = CommonUtilCn.readFile("news.txt");
            CommonUtilCn.printThreadLog(s);
        });

        CommonUtilCn.printThreadLog("here are not blocked,main continue");
        CommonUtilCn.sleepSecond(4);
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main4(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                String s = CommonUtilCn.readFile("news.txt");
                return s;
            }
        });
        CommonUtilCn.printThreadLog("here are not blocked,main continue");
        String s = future.get();
        CommonUtilCn.printThreadLog(s);
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main5(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return CommonUtilCn.readFile("news.txt");
        });
        CommonUtilCn.printThreadLog("here are not blocked,main continue");
        String s = future.get();
        CommonUtilCn.printThreadLog(s);
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main6(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<String> filterWordsFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("Filter Words");
            String filterWords = CommonUtilCn.readFile("filter_words.txt");
            return filterWords;
        });

        CompletableFuture<String[]> completableFuture = filterWordsFuture.thenApply((result) -> {
            CommonUtilCn.printThreadLog("Split Words");
            String[] splitWords = result.split(",");
            return splitWords;
        });

        String[] strings = completableFuture.get();
        CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(strings));
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main7(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<String[]> future = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("Filter Words");
            String filterWords = CommonUtilCn.readFile("filter_words.txt");
            return filterWords;
        }).thenApply((result) -> {
            CommonUtilCn.printThreadLog("Split Words");
            String[] splitWords = result.split(",");
            return splitWords;
        });

        String[] strings = future.get();
        CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(strings));
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main8(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("Filter Words");
            String filterWords = CommonUtilCn.readFile("filter_words.txt");
            return filterWords;
        }).thenApply((result) -> {
            CommonUtilCn.printThreadLog("Split Words");
            String[] splitWords = result.split(",");
            return splitWords;
        }).thenAccept((result) -> {
            CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(result));
        });

        CommonUtilCn.printThreadLog("main continue");
        CommonUtilCn.sleepSecond(5);
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main9(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("Filter Words");
            String filterWords = CommonUtilCn.readFile("filter_words.txt");
            return filterWords;
        }).thenApply((result) -> {
            CommonUtilCn.printThreadLog("Split Words");
            String[] splitWords = result.split(",");
            return splitWords;
        }).thenAccept((result) -> {
            CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(result));
        }).thenRun(() -> {
            CommonUtilCn.printThreadLog("DONE CompletableFuture");
        });

        CommonUtilCn.printThreadLog("main continue");
        CommonUtilCn.sleepSecond(5);
        CommonUtilCn.printThreadLog("main end");
    }

    // callback methods Async
    public static void main10(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");

        CompletableFuture<String[]> future = CompletableFuture.supplyAsync(() -> {
            return "ABC, DFE, GHJ";
        }).thenApply((result) -> {
            CommonUtilCn.printThreadLog("ThenApply CompletableFuture");
            String[] filterWords = result.split(",");
            return filterWords;
        });
        CommonUtilCn.printThreadLog("main continue");

        String[] filterWords = future.get();
        CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main11(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");

        CompletableFuture<String[]> future = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("SupplyAsync CompletableFuture");
            return CommonUtilCn.readFile("filter_words.txt");
        }).thenApplyAsync((result) -> {
            CommonUtilCn.printThreadLog("ThenApply CompletableFuture");
            String[] filterWords = result.split(",");
            return filterWords;
        });
        CommonUtilCn.printThreadLog("main continue");

        String[] filterWords = future.get();
        CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        CommonUtilCn.printThreadLog("main end");
    }

    public static void main12(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String[]> future = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("SupplyAsync CompletableFuture");
            return CommonUtilCn.readFile("filter_words.txt");
        }).thenApplyAsync((result) -> {
            CommonUtilCn.printThreadLog("ThenApply CompletableFuture");
            String[] filterWords = result.split(",");
            return filterWords;
        }, executor);
        CommonUtilCn.printThreadLog("main continue");

        String[] filterWords = future.get();
        CommonUtilCn.printThreadLog("filterWords = " + Arrays.toString(filterWords));
        CommonUtilCn.printThreadLog("main end");
        executor.shutdownNow();
    }

    public static void main13(String[] args) throws ExecutionException, InterruptedException {
        // combine asynchronous tasks - not work because return CompletableFuture<CompletableFuture<String[]>>
//        CompletableFuture<CompletableFuture<String[]>> completableFutureCompletableFuture
//                = readFileFuture("filter_words.txt").thenApply((result) -> {
//            return splitFuture(result);
//        });
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<String[]> future = readFileFuture("filter_words.txt")
                .thenCompose((result) -> {
                    CommonUtilCn.printThreadLog("ThenCompose ...");
                    return splitFuture(result);
                });

        String[] strings = future.get();
        CommonUtilCn.printThreadLog(Arrays.toString(strings));
    }

    public static CompletableFuture<String> readFileFuture(String fileName) {
        return CompletableFuture.supplyAsync(() -> {
            String filterWordsContent = CommonUtilCn.readFile(fileName);
            return filterWordsContent;
        });
    }

    public static CompletableFuture<String[]> splitFuture(String context) {
        return CompletableFuture.supplyAsync(() -> {
            String[] filterWords = context.split(",");
            return filterWords;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CommonUtilCn.printThreadLog("main start");
        CompletableFuture<String[]> filterWordsFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("SupplyAsync Filter Words ... ");
            String str = CommonUtilCn.readFile("filter_words.txt");
            String[] result = str.split(",");
            return result;
        });

        CompletableFuture<String> newsFuture = CompletableFuture.supplyAsync(() -> {
            CommonUtilCn.printThreadLog("SupplyAsync News ... ");
            String result = CommonUtilCn.readFile("news.txt");
            CommonUtilCn.printThreadLog(result);
            return result;
        });

        CompletableFuture<String> future = filterWordsFuture.thenCombine(newsFuture, (words, context) -> {
            CommonUtilCn.printThreadLog("ThenCombine ... ");
            for (String word : words) {
                if (context.indexOf(word) < 0) continue;
                context = context.replace(word, "**");
            }
            return context;
        });

        String strings = future.get();
        CommonUtilCn.printThreadLog(strings);
    }
}
