package com.neet.neetdesign.validator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RestResult<T> implements Serializable {

    Integer code;
    String message;
    T data;
    Boolean status;

    public RestResult() {
        this.code = 200;
        this.message = "success";
        this.data = null;
        this.status = true;
    }

    public RestResult(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
        this.status = true;
    }

    public RestResult(Boolean status) {
        this.code = 200;
        this.message = "success";
        this.data = null;
        this.status = status;
    }

    public RestResult(Boolean status, String message) {
        this.code = 200;
        this.message = message;
        this.data = null;
        this.status = status;
    }

    public RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
        this.status = false;
    }

    public static <T> RestResult<T> ok() {
        return new RestResult<>();
    }

    public static <T> RestResult<T> ok(T data) {
        return new RestResult<>(200, "success", data, true);
    }

    public static <T> RestResult<T> ok(T data, String message) {
        return new RestResult<>(200, message, data, false);
    }

    public static <T> RestResult<T> fail() {
        return new RestResult<>(400, "fail", null, false);
    }

    public static <T> RestResult<T> fail(String message) {
        return new RestResult<>(400, message, null, false);
    }

    public static <T> RestResult<T> fail(String message, Integer code) {
        return new RestResult<>(code, message, null, false);
    }

    public static <T> RestResult<T> limit() {
        return new RestResult<>(408, "访问频繁，请稍后再试");
    }

    public static <T> RestResult<T> notFound() {
        return new RestResult<>(404, "NOT FOUND");
    }
}
