package com.jiashn.scheduleTask.practices;

public interface IShape {
    int a = 15;
    int b = 20;
    void draw();

    default void show() {
        System.out.println("IShape default void show");
    }

    public static void test() {
        System.out.println("IShape method test()");
    }
}
