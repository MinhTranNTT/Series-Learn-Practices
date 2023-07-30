package com.example.testcommonvalidation.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
