package org.blog.service.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class OrderThreadProcessTest {
    @Test
    public void testCaseV1() {
        final AlternateDemo alternateDemo = new AlternateDemo();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    alternateDemo.loopA(i);
                }

            }
        }, "A").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    alternateDemo.loopB(i);
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    alternateDemo.loopC(i);
                    System.out.println("-----------------------");
                }
            }
        }, "C").start();

    }

    class AlternateDemo {
        private int number = 1;
        private Lock lock = new ReentrantLock();

        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        public void loopA(int total) {
            lock.lock();
            try {
                if (number != 1) {
                    condition1.await();
                }
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " \t" + i + "\t" + total);
                }
                number = 2;
                condition2.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void loopB(int total) {
            lock.lock();
            try {
                if (number != 2) {
                    condition2.await();
                }
                for (int i = 1; i <= 4; i++) {
                    System.out.println(Thread.currentThread().getName() + " \t" + i + "\t" + total);
                }
                number = 3;
                condition3.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void loopC(int total) {
            lock.lock();
            try {
                if (number != 3) {
                    condition3.await();
                }
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " \t" + i + "\t" + total);
                }
                number = 1;
                condition1.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
