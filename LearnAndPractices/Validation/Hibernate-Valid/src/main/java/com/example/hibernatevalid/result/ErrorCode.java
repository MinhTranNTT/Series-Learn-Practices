package com.example.hibernatevalid.result;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Here simply set the parameter status code '1000' error message to 'Parameter is incorrect'
    PARAM_ERROR("1000","The parameter is incorrect"),
    // You can also set other status codes and corresponding error messages here.
    SYSTEM_ERROR("9999","The SYSTEM_ERROR");
    private String code;
    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
