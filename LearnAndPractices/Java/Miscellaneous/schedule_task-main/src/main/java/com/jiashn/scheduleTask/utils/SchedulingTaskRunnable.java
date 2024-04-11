package com.jiashn.scheduleTask.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.Objects;

// Implement scheduled task threads
@Slf4j
@EnableAsync
public class SchedulingTaskRunnable<T> implements Runnable {
    /**
     * Other parameters
     */
    private final T other;
    /**
     * Scheduled tasks
     */
    private final String clazz;

    /**
     * Timed task method
     */
    private final String methodName;

    public SchedulingTaskRunnable(T other, String clazz, String methodName){
        this.other = other;
        this.clazz = clazz;
        this.methodName = methodName;
    }

    @Override
    public void run() {
        Object bean = SpringContextUtils.getBean(clazz);
        Method method;
        try{
            method = Objects.nonNull(other) ? bean.getClass().getDeclaredMethod(methodName,other.getClass()) :
                    bean.getClass().getDeclaredMethod(methodName);
            ReflectionUtils.makeAccessible(method);
            if (Objects.nonNull(other)){
                method.invoke(bean,other);
            } else {
                method.invoke(bean);
            }
        }catch (Exception e){
            log.error("Getting method information error：{}",e.getMessage());
        }
    }
}
