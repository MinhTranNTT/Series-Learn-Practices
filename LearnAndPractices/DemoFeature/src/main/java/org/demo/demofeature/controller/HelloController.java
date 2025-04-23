package org.demo.demofeature.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// @CrossOrigin(origins = "*")
@RequestMapping("/api/hello")
@RestController
@Slf4j
public class HelloController {
    @GetMapping("/demo1")
    public void getHello() {
       log.info("Hello World");
    }

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @GetMapping(value = "/bodyEmitter", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseBodyEmitter handle() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(-1L);
        CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    log.info("bodyEmitter {}", i);
                    LocalDateTime  localTime = LocalDateTime.now();
                    String dateStr = formatterLocalDate.format(localTime);
                    emitter.send("bodyEmitter " + i + " <===> " + dateStr + "\n");
                    Thread.sleep(1_000);
                }
                emitter.complete();
            } catch (Exception e) {
                log.error("{}", e.getMessage());
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @GetMapping(value = "/bodyEmitterAsync", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseBodyEmitter handleAsync() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(-1L);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    log.info("bodyEmitter {}", finalI);
                    LocalDateTime localTime = LocalDateTime.now();
                    String dateStr = formatterLocalDate.format(localTime);
                    emitter.send("bodyEmitter " + finalI + " <===> " + dateStr + "\n");
                    Thread.sleep(1_000);
                } catch (Exception e) {
                    log.error("{}", e.getMessage());
                    emitter.completeWithError(e);
                }
            });
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        emitter.complete();
        return emitter;
    }

    private static final Map<String, SseEmitter> EMITTER_MAP = new ConcurrentHashMap<>();

    @GetMapping("/subSseEmitter/{userId}")
    public SseEmitter sseEmitter(@PathVariable String userId) throws InterruptedException {
        log.info("sseEmitter: {}", userId);
        Thread.sleep(2_000);
        SseEmitter emitterTmp = new SseEmitter(-1L);
        EMITTER_MAP.put(userId, emitterTmp);
        CompletableFuture.runAsync(() -> {
            try {
                LocalDateTime localTime = LocalDateTime.now();
                String dateStr = formatterLocalDate.format(localTime);
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data("sseEmitter" + userId + " <===> " + dateStr)
                        .id(String.valueOf(userId))
                        .name("sseEmitter");
                emitterTmp.send(event);
            } catch (Exception ex) {
                emitterTmp.completeWithError(ex);
            }
        });
        return emitterTmp;
    }

    @GetMapping("/sendSseMsg/{userId}")
    public void sseEmitter(@PathVariable String userId, String msg) throws IOException {
        SseEmitter sseEmitter = EMITTER_MAP.get(userId);
        if (sseEmitter == null) {
            return;
        }
        sseEmitter.send(msg);
    }

}
