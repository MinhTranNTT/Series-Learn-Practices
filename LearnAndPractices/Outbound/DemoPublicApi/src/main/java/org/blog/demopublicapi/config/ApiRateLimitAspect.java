package org.blog.demopublicapi.config;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class ApiRateLimitAspect {
    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    @Before("@annotation(apiRateLimit)")
    public void limit(JoinPoint joinPoint, ApiRateLimit apiRateLimit) {
        String methodName = joinPoint.getSignature().toLongString();
        double qps = apiRateLimit.qps();
        RateLimiter limiter = rateLimiters.computeIfAbsent(methodName, k -> RateLimiter.create(qps));
        long timeout = apiRateLimit.timeout();
        TimeUnit timeUnit = apiRateLimit.timeUnit();
        if (timeout > 0) {
            if (!limiter.tryAcquire(timeout, timeUnit)) {
                throw new RuntimeException("API rate limit exceeded");
                // log.debug("API rate limit exceeded");
            }
        } else {
            if (!limiter.tryAcquire()) {
                throw new RuntimeException("API rate limit exceeded");
                // log.debug("API rate limit exceeded");
            }
        }
    }
}
