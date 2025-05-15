package org.crocodile.apptour.advice;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.crocodile.apptour.dto.Result;
import org.crocodile.apptour.entity.Limit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class LimitAop {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Around("@annotation(org.crocodile.apptour.entity.Limit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Limit limit = method.getAnnotation(Limit.class);
        if (limit != null) {
            String key = limit.key();
            RateLimiter rateLimiter = limitMap.computeIfAbsent(key, k -> RateLimiter.create(limit.permitsPerSecond()));
            boolean acquire = rateLimiter.tryAcquire(limit.timeout(), limit.timeunit());
            if (!acquire) {
                log.debug("Token bucket={} không lấy được token", key);
                this.responseFail(limit.msg());
                return null;
            }
        }
        return joinPoint.proceed();
    }

    private void responseFail(String msg) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Result<Object> fail = Result.fail();
        System.out.println(fail.toString());
    }

}
