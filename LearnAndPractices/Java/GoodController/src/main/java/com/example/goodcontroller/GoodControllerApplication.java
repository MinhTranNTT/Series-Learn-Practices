package com.example.goodcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.way02")
public class GoodControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodControllerApplication.class, args);
    }

}
