package com.example.mybatisday03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class MybatisDay03Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDay03Application.class, args);
    }

}
