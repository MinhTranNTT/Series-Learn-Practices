package com.example.testcommonvalidation.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseStatusExceptionCustomHandle extends RuntimeException{
    public ResponseStatusExceptionCustomHandle() {}

    public ResponseStatusExceptionCustomHandle(String message) {
        super(message);
    }
}
