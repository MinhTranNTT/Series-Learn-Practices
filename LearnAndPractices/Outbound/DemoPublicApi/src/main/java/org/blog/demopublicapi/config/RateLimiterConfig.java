package org.blog.demopublicapi.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {
    // @Bean
    // public RateLimiter apiRateLimiter() {
    //     return RateLimiter.create(200.0);
    // }
}
