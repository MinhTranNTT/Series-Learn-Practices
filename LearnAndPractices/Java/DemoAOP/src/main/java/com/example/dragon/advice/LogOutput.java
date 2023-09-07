package com.example.dragon.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class LogOutput implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
        String className = target.getClass().getName();
        String methodName = method.getName();

        System.out.println("Returning from method "+methodName+" of class "+className+" with "+returnValue.toString());
    }
}
