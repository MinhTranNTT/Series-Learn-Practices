package org.crocodile.apptour.controller;

import lombok.AllArgsConstructor;
import org.crocodile.apptour.dto.Result;
import org.crocodile.apptour.service.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/getHello")
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/getEnvironment")
    public Result<?> getEnvironment() {
        return helloService.getHello();
    }

}
