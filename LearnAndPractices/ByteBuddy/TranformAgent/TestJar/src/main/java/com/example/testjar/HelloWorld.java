package com.example.testjar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {
    @GetMapping("/hi")
    public void test() {
        System.out.println("Hello World MAIN");
        Init.init();
    }
}
