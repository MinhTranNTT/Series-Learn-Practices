package com.example.springsecuritysec3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.itdev")
public class Springsecuritysec3Application {

    public static void main(String[] args) {
        SpringApplication.run(Springsecuritysec3Application.class, args);
    }

}
