package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // plug-in paging
        return new PaginationInterceptor();
    }

//    @Bean
//    public PaginationInnerInterceptor paginationInterceptor() {
//        // plug-in paging
//        return new PaginationInnerInterceptor();
//    }
}
