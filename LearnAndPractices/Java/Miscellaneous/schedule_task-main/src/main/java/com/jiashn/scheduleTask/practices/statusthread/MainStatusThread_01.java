package com.jiashn.scheduleTask.practices.statusthread;

import java.util.Arrays;

public class MainStatusThread_01 {

    public static void main002(String[] args) {
        Arrays.stream(Thread.State.values()).forEach(System.out::println);
        // NEW
        // RUNNABLE
        // BLOCKED
        // WAITING
        // TIMED_WAITING
        // TERMINATED
    }

    public static void main003(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                // do something
            }
        });
        System.out.println("before start: " + t.getState());
        t.start();
        System.out.println("running: " + t.getState());
        Thread.sleep(1000);
        System.out.println("after start: " + t.getState());
    }

    public static void main001(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println("Open the refrigerator");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            System.out.println("Put the elephant in");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()->{
            System.out.println("Close the refrigerator");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        });
        t1.start();
        t1.join(); //The main thread waits for thread t1 to complete
        System.out.println("The refrigerator is open");

        t2.start();
        t2.join();//The main thread waits for thread t2 to complete
        System.out.println("The elephant is already inside");

        t3.start();
        t3.join();//Ensure that thread t3 is completed
        System.out.println("The fridge is closed, mission accomplished!");
    }

    public static void main(String[] args) {
        final Object object = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(object) {
                    while(true) {
                        try{
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t.start();
    }
}
