package com.parallelstepbystepcn.clientcn;

import com.parallelstepbystepcn.util.CommonUtilCn;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FutureDemo {
    private static ExecutorService executor = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String[]> filterWordFuture = executor.submit(() -> {
            String s = CommonUtilCn.readFile("filter_words.txt");
            String[] filterWords = s.split(",");
            return filterWords;
        });

        Future<String> newsFuture = executor.submit(() -> {
            return CommonUtilCn.readFile("news.txt");
        });

        Future<String> replaceFuture = executor.submit(() -> {
            try {
                String[] words = filterWordFuture.get();
                String news = newsFuture.get();

                for (String word : words) {
                    if (news.indexOf(word) < 0) continue;
                    news = news.replace(word, "**");
                }
                return news;
//                Arrays.stream(words)
//                        .filter((word) -> news.indexOf(word) >= 0)
//                        .filter((word) -> news = news.replace(word, "**"))
//                        .collect(Collectors.toList());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        String filteredNews = replaceFuture.get();
        System.out.println("New String " + filteredNews);
        executor.shutdownNow();
    }
}
