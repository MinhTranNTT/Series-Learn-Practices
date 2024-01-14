package com.neet.neetdesign.behavioural.observer;

public class ObserverMain {
    public static void main(String[] args) {
        Store store = new BookStore();

        // Create email notifiers (observers) for two customers
        Customer customer1 = new BookCustomer(store);
        Customer customer2 = new BookCustomer(store);

        // Initially, the book is out of stock
        System.out.println("Setting stock to 0.");
        store.updateQuantity(0);
        // The book comes back in stock
        System.out.println("\nSetting stock to 5.");
        store.updateQuantity(5);

        // Remove customer1 from the notification list
        store.removeCustomer(customer1);

        // Simulate the situation where the stock changes again
        System.out.println("\nSetting stock to 2.");
        store.updateQuantity(2);
    }
}
