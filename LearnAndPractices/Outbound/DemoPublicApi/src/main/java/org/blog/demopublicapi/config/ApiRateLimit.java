package org.blog.demopublicapi.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiRateLimit {
    double qps() default 1; // 每秒钟生成令牌的速率
    long timeout() default 0; // 尝试获取令牌的超时时间
    TimeUnit timeUnit() default TimeUnit.SECONDS; // 超时时间单位
}
