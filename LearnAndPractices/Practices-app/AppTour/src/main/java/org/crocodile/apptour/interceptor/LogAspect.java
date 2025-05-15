package org.crocodile.apptour.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// @Aspect
// @Component
// @Order(1)
public class LogAspect {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * org.crocodile.apptour.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        log.info("============= @Before Thông báo trước begin =============");
        log.info("Thực hiện kiểm tra quyền hạn...");
        log.info("------------- Thông tin mục tiêu -------------");
        log.info("Lớp mục tiêu: " + joinPoint.getTarget());
        log.info("Tên phương thức: " + joinPoint.getSignature().getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("Phương thức: " + request.getMethod());
        log.info("URL: " + request.getRequestURL());
        log.info("IP khách hàng: " + request.getRemoteAddr());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("============= @AfterReturning Thông báo sau begin =============");
        log.info("Giá trị trả về: " + ret);
    }

    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        log.info("============= @AfterThrowing Thông báo lỗi begin =============");
    }

    @After("webLog()")
    public void after(JoinPoint jp) {
        log.info("============= @After Thông báo cuối begin =============");
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("============= @Around Thông báo xung quanh begin =============");
        Object obj;
        try {
            log.info("Bắt đầu thực thi phương thức mục tiêu...");
            obj = pjp.proceed(pjp.getArgs());
            log.info("Thực thi phương thức thành công: " + obj);
        } catch (Throwable e) {
            log.info("Lỗi khi thực thi phương thức mục tiêu...");
            throw new RuntimeException(e);
        }
        return obj;
    }
}
