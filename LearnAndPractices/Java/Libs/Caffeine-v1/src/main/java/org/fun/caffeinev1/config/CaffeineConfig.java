package org.fun.caffeinev1.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class CaffeineConfig {

    @Bean("cacheCaffeineManager")
    public Cache<String, String> cacheCaffeineManager() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .maximumSize(5)
                .build();
        log.info("Init cache manage {}", cache);
        return cache;
    }
    
}
