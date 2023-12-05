package com.practices.test01;

public class PerformanceTest {
    public static void main(String[] args) {

        // Hey! I'm waiting you to switch to VisualVM
        // and see I'm also in there
        // just in 20 seconds
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            PerformanceClass testClass = new PerformanceClass();
            testClass.foo1();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class PerformanceClass {

    private int mField = 10;

    public void foo1() {
        mField += 3;
        System.out.println("foo1()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        foo2();
        foo3();
    }

    public void foo2() {
        mField += 2;
        System.out.println("foo2()");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        foo3();
    }

    public void foo3() {
        mField += 1;
        System.out.println("foo3()");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
