package com.practices.demoxms;

import java.util.ArrayList;

//-Xms  设置初始化内存大小，默认是1/64
//-Xms  设置最大分配内存，默认是1/4
//-XX:+PrintGCDetail    打印GC垃圾回收的相关信息
//-XX:+HeapDumpOnOutOfMemoryError   打印OOM的错误，并Dump
//-Xms1m -Xms8m -XX:+HeapDumpOnOutOfMemoryError
// -Xms1m -Xms8m -XX:+HeapDumpOnOutOfMemoryError
public class Demo03 {
    byte[] bytes = new byte[1*1024*1024];

    public static void main(String[] args) {
        ArrayList<Demo03> objects = new ArrayList<>();

        int count = 0;

        try {
            while (true) {
                objects.add(new Demo03());
                count++;
            }
        } catch (Exception e) {
            System.out.println(count);
            System.out.println(e.getMessage());
        }
    }
}
