package org.blog.service.timeout;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class FutureTimeoutV3Test {
    @Test
    public void getAsync() {
        ThreadPoolTaskExecutor executor = taskExecutor();
        List<Integer> data = getData(1_000);
        List<List<Integer>> partition = Lists.partition(data, 100);

        List<Future<List<String>>> futures = Collections.synchronizedList(new ArrayList<>());
        for (List<Integer> listNumber : partition) {
            Future<List<String>> future = executor.submit(() -> handlerTransformData(listNumber));
            futures.add(future);
        }

        List<List<String>> result = Collections.synchronizedList(new ArrayList<>());
        for (Future<List<String>> future : futures) {
            try {
                List<String> list = future.get(2, TimeUnit.SECONDS);
                result.add(list);
            } catch (Exception e) {
                log.warn("Task exceeded individual timeout, skipping : {}", Thread.currentThread().getName());
                future.cancel(true);
            }
        }

        for (List<String> itemResult : result) {
            System.out.println(itemResult);
        }
    }

    public <T> T runWithTimeout(Callable<T> task, int timeout, TimeUnit unit) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(task);
        try {
            return future.get(timeout, unit);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw e;
        } finally {
            executor.shutdownNow();
        }
    }

    public List<String> handlerTransformData(List<Integer> data) {
        List<String> listTxt = new ArrayList<>();
        for (Integer number : data) {
            // if (Thread.currentThread().isInterrupted()) {
            //     log.warn("Thread was interrupted, stopping early for data: {}", number);
            //     return Collections.emptyList();
            // }
            if (number % 500 == 0) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
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
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(10);
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
