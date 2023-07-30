package com.example.testcommonvalidation.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String errorTypeName;

    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponse(String errorTypeName, String message) {
        this.message = message;
        this.errorTypeName = errorTypeName;
    }

}
