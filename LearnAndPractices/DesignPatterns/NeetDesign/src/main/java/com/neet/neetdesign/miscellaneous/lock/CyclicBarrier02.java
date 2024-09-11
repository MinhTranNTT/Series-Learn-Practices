package com.neet.neetdesign.miscellaneous.lock;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier02 {
    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread{

        @Override
        public void run() {
            System.out.println("Player " + Thread.currentThread().getName() + " loading 100%");
            //await
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            public void run() {
                System.out.println("All player loading done，start game....");
            }
        });

        for(int i = 0 ; i < 10 ; i++){
            new CyclicBarrierThread().start();
        }
    }
}
