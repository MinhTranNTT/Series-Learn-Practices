package org.crocodile.apptour.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
public class DemoConcurrencyGuava {

    @Test
    public void testing() {
        ListenableFuture<String> future = DemoConcurrencyGuava.search113(1);
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {}
        }, pool);
    }

    public static ListenableFuture<String> search113(int key) {
        ListenableFuture<String> future = pool.submit(() -> query(key));
        return Futures.catching(future, Throwable.class, (t) -> "-1", pool);
    }

    public String query123(int key) throws Exception {
        return "result" + key;
    }

    private static ListeningExecutorService pool =
            MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

    public SettableFuture<String> search(int key) {
        SettableFuture<String> response = SettableFuture.create();
        ListenableFuture<String> future = pool.submit(() -> query(key));

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                response.set(result);
            }

            @Override
            public void onFailure(Throwable t) {
                response.setException(t);
            }
        }, pool);

        return response;
    }

    private static String query(int key) throws Exception {
        throw new Exception("error occurred");
    }

    @Test
    public void addListenerTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(() -> 1);
        // not get result on ListenableFuture
        listenableFuture.addListener(() -> System.out.println("Ended !!!"), executorService);
        System.out.println("Hello World");
    }

    @Test
    public void addCallbackTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ListeningExecutorService es = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<Integer> listenableFuture = es.submit(() -> 1);

        Futures.addCallback(listenableFuture, new FutureCallback<>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("onSuccess - " + result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, executorService);
        System.out.println("Hello World");
    }

    @Test
    public void addCallbackFailTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ListeningExecutorService es = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<Integer> listenableFuture = es.submit(() -> {
            if (true)
                throw new Exception("Error");
            return 1;
        });

        Futures.addCallback(listenableFuture, new FutureCallback<>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("onSuccess - " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("onFailure - " + t.getMessage());
            }
        }, executorService);
        System.out.println("Hello World");
    }

}
