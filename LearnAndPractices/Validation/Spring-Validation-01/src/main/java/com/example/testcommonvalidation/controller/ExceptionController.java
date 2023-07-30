package com.example.testcommonvalidation.controller;

import com.example.testcommonvalidation.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExceptionController {

    @GetMapping("/illegalArgumentException")
    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/resourceNotFoundException")
    public void throwResourceNotFoundException() {
        throw new ResourceNotFoundException();
    }

}
