package com.example.mybatisday02;

import com.example.config.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.example.mapper")
@ComponentScan("com.example.config")    // setup scan config package or @Import(MybatisPlusConfig.class)
public class MybatisDay02Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDay02Application.class, args);
    }

}
