package com.example.buddy_02;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MonitorDemo02 {
    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        Object resObj = null;
        try {
            resObj = callable.call();
            return resObj;
        } finally {
            System.out.println("Method name：" + method.getName());
            System.out.println("Number of input parameters：" + method.getParameterCount());
            System.out.println("Input parameter type：" + method.getParameterTypes()[0].getTypeName() + "," + method.getParameterTypes()[1].getTypeName());
            System.out.println("Parameter type：" + method.getReturnType().getName());
            System.out.println("Parameter result：" + resObj);
            System.out.println("Method takes time：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
