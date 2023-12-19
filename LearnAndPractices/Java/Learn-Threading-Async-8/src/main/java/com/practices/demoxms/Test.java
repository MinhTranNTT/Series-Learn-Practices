package com.practices.demoxms;

public class Test {
    public static void main(String[] args) {
        //Returns the maximum memory the virtual machine is trying to use
        long max = Runtime.getRuntime().maxMemory();
        //Returns the initial memory of the virtual machine
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("Maximum memory the virtual machine is trying to use:" + max +
                " byte, " + ((double)max/1024/1024) + " MB");
        System.out.println("Initial memory of the virtual machine:" + totalMemory +
                " byte, " + ((double)totalMemory/1024/1024) + " MB");

        //在启动时配置JVM参数
        //-Xms8m -Xms8m -XX:+PrintGCDetails

        //伊甸园区+老年区=堆内存  元空间 逻辑上存在，物理上不存在
        //305664K + 699392K = 1,005,056K = 981.5MB

        //默认情况下，分配的总内存 是电脑内存的 1/4 而初始内存是 1/64

        //OOM错误解决
        //1.尝试扩大堆内存，看结果
        //2.分析内存，看那个地方出现了问题（专业工具）
    }
}
