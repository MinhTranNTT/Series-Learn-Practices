package com.example.agentjava.transform;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.springframework.asm.Type;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class RunTimeTransformer implements ClassFileTransformer {
    private static final String INJECTED_CLASS = "com.example.testjar.HelloWorld";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        String realClassName = className.replace("/", ".");

        if (!realClassName.equals(INJECTED_CLASS))  return classfileBuffer;

        System.out.println("Intercepted class name: " + realClassName);
        CtClass ctClass;
        StringBuilder builder = new StringBuilder("");
        try {
            // Use javassist to get the bytecode class
            ClassPool classPool = ClassPool.getDefault();
            ctClass = classPool.get(realClassName);

            // Get all method instances of this class, you can also select methods to enhance
            CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            for (CtMethod method : declaredMethods) {
                builder.setLength(0);
                builder.append(method.getDeclaringClass().getPackageName());
                System.out.println(method.getName() + "Method intercepted");
                method.addLocalVariable("time", CtClass.longType);
                method.insertBefore("{ System.out.println(\"---Start execution---: function" + method.getName() + "(); package: "
                        + String.valueOf(builder) + "\"); }");
                method.insertBefore("time = System.currentTimeMillis();");
                method.insertAfter("System.out.println(\"---end execution---\");");
                method.insertAfter("System.out.println(\"Running time: \" + (System.currentTimeMillis() - time));");
            }
            return ctClass.toBytecode();
        } catch (Throwable e) { // Use Throwable here instead of Exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return classfileBuffer;
    }
}
