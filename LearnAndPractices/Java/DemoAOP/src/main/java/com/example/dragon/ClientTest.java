package com.example.dragon;

import com.example.dragon.service.LibraryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myLibraryAppContext.xml");
        LibraryService myLibraryService = (LibraryService) applicationContext.getBean("libraryServiceProxy");
//        myLibraryService.issueBook(1, 1);
//        myLibraryService.returnBook(2, 2);
        myLibraryService.addBook(3);
    }
}
