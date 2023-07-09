package com.example.createthread01;

public class _01_Create_Thread_Impl_Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> System.out.println(Thread.currentThread().getName()+ " Child")
        );

        System.out.println(Thread.currentThread().getName() + " Main");
    }
}
