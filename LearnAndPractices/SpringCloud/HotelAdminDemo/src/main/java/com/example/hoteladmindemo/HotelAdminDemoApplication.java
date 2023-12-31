package com.example.hoteladmindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.admin")
@MapperScan("com.example.admin.mapper")
public class HotelAdminDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelAdminDemoApplication.class, args);
    }

}
