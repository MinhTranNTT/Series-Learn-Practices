package com.neet.neetdesign.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

public class BookStore implements Store {
    private int stockQuantity = 0;
    private List<Customer> customers = new ArrayList<>();

    @Override
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    @Override
    public void removeCustomer(Customer c) {
        customers.remove(c);
    }

    @Override
    public void notifyCustomers() {
        customers.forEach(customer -> customer.update(stockQuantity));
    }

    @Override
    public void updateQuantity(int quantity) {
        this.stockQuantity = quantity;
        notifyCustomers();
    }
}
