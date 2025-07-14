package org.blog.demopublicapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blog.demopublicapi.config.ApiRateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/getHello")
@AllArgsConstructor
@Slf4j
public class HelloController {

    // private final RateLimiter rateLimiter;
    //
    // @GetMapping
    // public String getHello() {
    //     if (!rateLimiter.tryAcquire()) {
    //         log.info("Qua nhieu yeu cau");
    //     }
    //     return "Hello";
    // }

    @GetMapping
    @ApiRateLimit(qps = 2, timeout = 200, timeUnit = TimeUnit.MILLISECONDS)
    public String test() {
        return "hello world";
    }

}
