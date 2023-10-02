package com.example.mybatisday07;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.dragon")
@MapperScan("com.example.dragon.dao")
public class MybatisDay07Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDay07Application.class, args);
    }

}
