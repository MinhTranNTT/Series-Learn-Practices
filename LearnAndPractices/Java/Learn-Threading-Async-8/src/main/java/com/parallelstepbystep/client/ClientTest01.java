package com.parallelstepbystep.client;

import com.parallelstepbystep.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.parallelstepbystep.util.CommonUtil.delay;
import static com.parallelstepbystep.util.LoggerUtil.log;

public class ClientTest01 {
    public static void main(String[] args) {
        HelloWorldService hws = new HelloWorldService();
        CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenApply(String::toUpperCase)
                .thenAccept((result) -> log("Result is " + result))
                .join();

        log("DONE");
//        delay(2000);
    }
}
