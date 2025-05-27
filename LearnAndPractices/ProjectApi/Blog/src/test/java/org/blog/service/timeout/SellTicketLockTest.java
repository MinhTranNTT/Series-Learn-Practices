package org.blog.service.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class SellTicketLockTest {
    @Test
    public void testCaseV1() throws InterruptedException {
        SellTicket sellTicket = new SellTicket();
        // Thread threadA = new Thread(sellTicket, "ThreadNameA");
        // Thread threadB = new Thread(sellTicket, "ThreadNameB");
        // Thread threadC = new Thread(sellTicket, "ThreadNameC");
        // Thread threadD = new Thread(sellTicket, "ThreadNameD");
        // Thread threadE = new Thread(sellTicket, "ThreadNameE");
        //
        // threadA.start();
        // threadB.start();
        // threadC.start();
        // threadD.start();
        // threadE.start();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(sellTicket, executor).join();
        }

        // TimeUnit.SECONDS.sleep(25);
    }

    class SellTicket implements Runnable {
        private int ticketNum = 100;
        private Lock lock = new ReentrantLock();

        @Override
        public void run() {
            while (ticketNum > 0) {
                try {
                    lock.lock();
                    if (ticketNum > 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + " Con lai - " + (ticketNum--));
                    }
                    else if (ticketNum <= 0) {
                        System.out.println("Out of stock!!!");
                    }
                } finally {
                    lock.unlock();
                }
            }
            if (ticketNum <= 0) {
                System.out.println("Out of stock");
            }
        }
    }
}

