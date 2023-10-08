package com.example.buddy_01;

import com.example.buddy_01.dragon01.GenerateClazzMethod;
import com.example.buddy_01.dragon01.Hi;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.named;

@SpringBootTest
class Buddy01ApplicationTests {
    @Test
    void test01() throws InstantiationException, IllegalAccessException {
        String helloWorld = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);  // Hello World!
    }

    private static void outputClazz(byte[] bytes) {
        FileOutputStream out = null;
        try {
            String pathName = Buddy01ApplicationTests.class.getResource("/").getPath() + "ByteBuddyHelloWorld.class";
            out = new FileOutputStream(new File(pathName));
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_helloWorld() throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("org.itstack.demo.bytebuddy.HelloWorld")
                .method(named("toString")).intercept(FixedValue.value("Hello World!"))
                .make();

        // Output class bytecode
        outputClazz(dynamicType.getBytes());

        String toString = dynamicType.load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(toString);
    }

    @Test
    void test03() throws InstantiationException, IllegalAccessException {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("org.itstack.demo.bytebuddy.HelloWorld")
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class, "args")
                .intercept(FixedValue.value("Hello World!"))
                .make();

        // Output class bytecode
        outputClazz(dynamicType.getBytes());

        String toString = dynamicType.load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(toString);
    }

    @Test
    public void test05(){
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("org.itstack.demo.bytebuddy.HelloWorld")
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class, "args")
                .intercept(MethodDelegation.to(Hi.class))
                .make();

        // 输出类字节码
        outputClazz(dynamicType.getBytes());
    }

    @Test
    public void test06() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //Create class
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("org.itstack.demo.bytebuddy.HelloWorld")                          //Class name information
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC) // Define method
                .withParameter(String[].class, "args")                                 // Setting parameters
                .intercept(MethodDelegation.to(Hi.class))
                .defineField("str", String.class, Modifier.PUBLIC)
                .make();

        //Load class
        Class<?> clazz = dynamicType.load(GenerateClazzMethod.class.getClassLoader())
                .getLoaded();

        // Output class bytecode
        outputClazz(dynamicType.getBytes());

        // Reflection call
        clazz.getMethod("main", String[].class).invoke(clazz.newInstance(), (Object) new String[1]);
    }

}
