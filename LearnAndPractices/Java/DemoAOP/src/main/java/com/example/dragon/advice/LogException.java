package com.example.dragon.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LogException implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        String className = target.getClass().getName();
        String methodName = method.getName();

        System.out.println("Throwing exception from method "+methodName+" of class "+className);
        System.out.println("Exception message is "+ex.getMessage());
    }
}
