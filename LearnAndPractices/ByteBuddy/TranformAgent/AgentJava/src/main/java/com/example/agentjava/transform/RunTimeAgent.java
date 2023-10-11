package com.example.agentjava.transform;

import java.lang.instrument.Instrumentation;

public class RunTimeAgent {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("The args parameter is passed to the Java Agent: " + args);
        instrumentation.addTransformer(new RunTimeTransformer());
    }
}
