package com.cj.concurrencystep.step01.threadhandlerex;

public class ThreadExceptionCatch {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is test");
                int i = 10/0;
            }
        });
        // # classic
//        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            // Here, the Throwable object is monitored, so whether it is an error or an exception, it can be identified.
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.err.println("thread is "+t.getName());
//                e.printStackTrace();
//            }
//        });
        // lamda
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("thread is " + t.getName());
            e.printStackTrace();
        });
        thread.start();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("MinhTran");
    }
}
