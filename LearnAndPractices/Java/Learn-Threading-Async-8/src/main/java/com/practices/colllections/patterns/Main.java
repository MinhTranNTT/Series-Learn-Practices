package com.practices.colllections.patterns;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        System.out.println(order.getState());
        order.nextState();

        System.out.println(order.getState());
        order.nextState();

        System.out.println(order.getState());
        order.nextState();

        // BigDecimal bigDecimal1 = new BigDecimal(0.1);
        // BigDecimal bigDecimal2 = BigDecimal.valueOf(0.1);
        // System.out.println("BigDecimal1: " + bigDecimal1);
        // System.out.println("BigDecimal2: " + bigDecimal2);
    }
}
