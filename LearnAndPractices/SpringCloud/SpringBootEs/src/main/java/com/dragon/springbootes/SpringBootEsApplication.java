package com.dragon.springbootes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dragon.primary")
@MapperScan("com.dragon.primary.mapper")
public class SpringBootEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEsApplication.class, args);
    }

}
