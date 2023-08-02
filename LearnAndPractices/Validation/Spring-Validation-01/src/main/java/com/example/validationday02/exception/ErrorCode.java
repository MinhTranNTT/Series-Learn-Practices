package com.example.validationday02.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//@Getter
public enum ErrorCode {
    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "The resource was not found"),
    REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "Request data format validation failed");
    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
