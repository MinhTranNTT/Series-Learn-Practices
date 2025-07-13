package org.blog.demopublicapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getHello")
@Slf4j
public class HelloController {

    @GetMapping
    public String getHello() {
        return "Hello";
    }

}
