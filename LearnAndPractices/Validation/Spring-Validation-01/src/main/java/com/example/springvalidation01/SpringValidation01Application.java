package com.example.springvalidation01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.example.testcommonvalidation", "com.example.validationday02" }) // outside package @SpringBootApplication
public class SpringValidation01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringValidation01Application.class, args);
    }

}
