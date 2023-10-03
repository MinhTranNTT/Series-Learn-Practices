package com.practices.test01;

import java.util.Vector;
import java.util.concurrent.*;

public class a0_Test {
    private ProcessOrder pos;
    private DeliveryOrder dos;

    public void Main_Join() throws InterruptedException {

        while (existUnCheckOrders()) {
            Thread T1 = new Thread(() -> {
                pos = getPOrders();
            });
            T1.start();

            Thread T2 = new Thread(() -> {
                dos = getDOrders();
            });
            T2.start();

            T1.join();
            T2.join();

            Order diff = checkOrder(pos, dos);
            save(diff);
        }
    }

    /**
     * Use CountDownLatch to keep threads in lockstep
     * Advantages: Using the thread pool reduces the time-consuming thread creation and improves thread utilization.
     * Disadvantage: It can only be checked once, and CountDownLatch will be reset to zero.
     * @throws InterruptedException
     */
    public void testCountDownLatch() throws InterruptedException {
//      Create a thread pool of 2 threads
//      Executor executor = Executors.newFixedThreadPool(2);  // Try to use new ThreadPoolExecutor to explicitly create a thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

        while (existUnCheckOrders()) {
            CountDownLatch latch = new CountDownLatch(2);
            executor.execute(() -> {
                pos = getPOrders();
                latch.countDown();
            });
            executor.execute(() -> {
                dos = getDOrders();
                latch.countDown();
            });
            // Wait for the two query operations to end
            latch.await();

            Order diff = checkOrder(pos, dos);
            save(diff);
        }
    }

    //@Test
    public void testCyclicBarrier() {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        /**
         * You only need to call the checkAll method once. After the execution of the two threads is completed,
         * the check method in CyclicBarrier will be called back for reconciliation.
         *
         * The executor thread pool size should be 1,
         * Reason 1: The check method takes much less time than the getOrder method, so one thread can handle it.
         * Reason 2: If multiple threads perform check, it may cause the POrder of thread A and the DOrder of thread B to be checked at a certain moment,
         * causing data confusion.
         */
        cyclicBarrierTest.checkAll();
    }

    class CyclicBarrierTest {
        // order queue
        Vector<ProcessOrder> pos;
        // Dispatch order queue
        Vector<DeliveryOrder> dos;
        // Thread pool for executing callbacks
        Executor executor = Executors.newFixedThreadPool(1);
        final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            executor.execute(() -> check());
        });


        private void check() {
            ProcessOrder p = pos.remove(0);
            DeliveryOrder d = dos.remove(0);
            Order diff = checkOrder(p, d);
            save(diff);
        }

        public void checkAll() {
            Thread T1 = new Thread(() -> {
                while (existUnCheckOrders()) {
                    // Query waybill database
                    pos.add(getPOrders());
                    // wait
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            T1.start();

            Thread T2 = new Thread(() -> {
                while (existUnCheckOrders()) {
                    // Query waybill database
                    dos.add(getDOrders());
                    // wait
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            T2.start();
        }

    }


    class ProcessOrder {    }

    class DeliveryOrder {    }

    class Order {    }

    private void save(Order diff) {
    }

    private Order checkOrder(ProcessOrder pos, DeliveryOrder dos) {
        return new Order();
    }

    private DeliveryOrder getDOrders() {
        return new DeliveryOrder();
    }

    private ProcessOrder getPOrders() {
        return new ProcessOrder();
    }

    private boolean existUnCheckOrders() {
        return true;
    }

}


