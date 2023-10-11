package com.cj.concurrencystep.step01.basic;

public class SystemExit {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("close1");
        }));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("close2");
        }));

        Thread daemonThread = new Thread(() -> {
            System.out.println("this is daemon thread start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is daemon thread end");
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
//        try {
//            daemonThread.join();    // wait process in task end
//        } catch (InterruptedException e) {
//            System.out.println("Error");
//        }
        System.exit(1);
        System.out.println("Not run this line");
    }
}
