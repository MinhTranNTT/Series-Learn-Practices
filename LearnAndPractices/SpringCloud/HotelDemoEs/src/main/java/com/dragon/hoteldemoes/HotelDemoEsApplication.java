package com.dragon.hoteldemoes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dragon.it")
@MapperScan("com.dragon.it.mapper")
public class HotelDemoEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelDemoEsApplication.class, args);
    }

}
