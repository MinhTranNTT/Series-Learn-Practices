package com.neet.neetdesign.behavioural.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class CommandDemo {
    public static void main1(String[] args) {
        Stock stock1 = new Stock("Google", 10);

        BuyStock buyStock = new BuyStock(stock1);
        SellStock sellStock = new SellStock(stock1);

        Broker broker = new Broker();

        broker.addOrder(buyStock);
        broker.addOrder(buyStock);
        broker.addOrder(sellStock);

        broker.placeOrders();
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Minh", "Tom", "Harry");
        Enumeration<String> enumeration = Collections.enumeration(list);
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }

}
