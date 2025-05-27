package org.blog.service.timeout;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class FutureTest {
    @Test
    public void getAsync() {
        ThreadPoolTaskExecutor executor = taskExecutor();
        List<Integer> data = getData(1_000);
        List<List<Integer>> partition = Lists.partition(data, 100);
        List<CompletableFuture<List<String>>> listFuture = Collections.synchronizedList(new ArrayList<>());
        for (List<Integer> listNumber : partition) {
            CompletableFuture<List<String>> future = CompletableFuture
                    .supplyAsync(() -> handlerTransformData(listNumber), executor);
            listFuture.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(listFuture.toArray(new CompletableFuture[0]));
        allOf.join();
        List<String> result = listFuture.stream().map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public List<String> handlerTransformData(List<Integer> data) {
        List<String> listTxt = new ArrayList<>();
        for (Integer number : data) {
            if (number % 500 == 0) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    log.error("InterruptedException number {}", number);
                    Thread.currentThread().interrupt();
                }
            }
            listTxt.add(number + " stock");
        }
        return listTxt;
    }

    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(10);
        executor.setThreadNamePrefix("WorkerExecutor::");

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    private List<Integer> getData(int n) {
        return IntStream.rangeClosed(1, n).boxed().sorted().collect(Collectors.toList());
    }
}
