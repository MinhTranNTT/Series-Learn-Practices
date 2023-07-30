package com.example.mybatisday02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class MybatisDay02Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDay02Application.class, args);
    }

}
