package com.example.springrest.otherWay;

import com.example.springrest.enums.HelloReturnCode;

@FunctionalInterface
public interface HelloValidator<T> {
    HelloReturnCode validate(T value);
}
