package com.example.dragon.service;

public class LibraryService {
    public boolean issueBook(int memberID, int bookID) {
        boolean status = false;
        // Business logic to issue a book from Library

        return status;
    }

    public boolean returnBook(int memberID, int bookID) {
        boolean status = false;
        // Business logic to return the issues book

        return status;
    }

    public boolean addBook(int bookID) throws InterruptedException {
        boolean status = false;
        // Business logic to return the issues book
        Thread.sleep(5000);
        sumTwoNumber();
        return status;
    }

    private void sumTwoNumber() throws InterruptedException {
        Thread.sleep(3000);
    }
}
