package com.cj.concurrencystep.step01.priority;

public class ThreadPriorityDemo {
    static class InnerTask implements Runnable {
        private int i;

        public InnerTask(int i) {
            this.i = i;
        }

        public void run() {
            for(int j=0; j < 10; j++){
                System.out.println("ThreadName is " + Thread.currentThread().getName() + " " + j);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Priority 1(lowest) -> 10 (highest)
        // default priority is 5

        Thread t1 = new Thread(new InnerTask(10),"task-1");
        t1.setPriority(1);
        Thread t2 = new Thread(new InnerTask(2),"task-2");
        // Priority can only be used as a reference value,
        // and the specific thread priority is also related to the operating system.
        t2.setPriority(2);
        Thread t3 = new Thread(new InnerTask(3),"task-3");
        t3.setPriority(3);

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(2000);
    }
}
