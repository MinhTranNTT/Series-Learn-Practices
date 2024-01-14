package com.neet.neetdesign.behavioural.observer;

public interface Store {
    void addCustomer(Customer c);
    void removeCustomer(Customer c);
    void notifyCustomers();
    void updateQuantity(int quantity);
}
