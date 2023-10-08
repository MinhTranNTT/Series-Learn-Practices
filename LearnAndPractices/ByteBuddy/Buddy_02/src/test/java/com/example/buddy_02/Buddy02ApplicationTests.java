package com.example.buddy_02;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Buddy02ApplicationTests {
    @Test
    public void test_byteBuddy() throws Exception {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(BizMethod.class)
                .method(ElementMatchers.named("queryUserInfo"))
                .intercept(MethodDelegation.to(Monitor.class))
                .make();

        // Load class
        Class<?> clazz = dynamicType.load(Buddy02ApplicationTests.class.getClassLoader())
                .getLoaded();

        // Reflection call
        clazz.getMethod("queryUserInfo", String.class, String.class).invoke(clazz.newInstance(), "10001", "Adhl9dkl");
    }

    @Test
    public void test_byteBuddy2() throws Exception {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(BizMethod.class)
                .method(ElementMatchers.named("queryUserInfo"))
                .intercept(MethodDelegation.to(MonitorDemo.class))
                .make();

        // Load class
        Class<?> clazz = dynamicType.load(Buddy02ApplicationTests.class.getClassLoader())
                .getLoaded();

        // Reflection call
        clazz.getMethod("queryUserInfo", String.class, String.class).invoke(clazz.newInstance(), "10001", "Adhl9dkl");
    }

    @Test
    public void test_byteBuddy3() throws Exception {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(BizMethod.class)
                .method(ElementMatchers.named("queryUserInfo"))
                .intercept(MethodDelegation.to(MonitorDemo02.class))
                .make();

        // Load class
        Class<?> clazz = dynamicType.load(Buddy02ApplicationTests.class.getClassLoader())
                .getLoaded();

        // Reflection call
        clazz.getMethod("queryUserInfo", String.class, String.class).invoke(clazz.newInstance(), "10001", "Adhl9dkl");
    }

    @Test
    public void test_byteBuddy4() throws Exception {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(BizMethod.class)
                .method(ElementMatchers.named("queryUserInfo"))
                .intercept(MethodDelegation.to(MonitorDemo03.class))
                .make();

        // Load class
        Class<?> clazz = dynamicType.load(Buddy02ApplicationTests.class.getClassLoader())
                .getLoaded();

        // Reflection call
        clazz.getMethod("queryUserInfo", String.class, String.class).invoke(clazz.newInstance(), "10001", "Adhl9dkl");
    }

}
