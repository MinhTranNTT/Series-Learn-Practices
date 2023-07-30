package com.example.testcommonvalidation.controller;

import com.example.testcommonvalidation.entity.ResponseStatusExceptionCustomHandle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ResponseStatusExceptionCustomController {

    @GetMapping("/responseStatusExceptionCustomController")
    public void throwResponseStatusExceptionCustomController() {
        throw new ResponseStatusExceptionCustomHandle("Sorry, the resource not found!");
    }

    @GetMapping("/responseStatusExceptionCustomController2")
    public void throwResponseStatusExceptionCustomController2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, the resource not found!");
    }

}
