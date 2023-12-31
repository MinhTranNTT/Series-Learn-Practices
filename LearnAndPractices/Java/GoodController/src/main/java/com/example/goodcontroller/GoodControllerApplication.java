package com.example.goodcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.example.way02")
@EnableScheduling
public class GoodControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodControllerApplication.class, args);
    }

}
