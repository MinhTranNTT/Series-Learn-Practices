package com.practices.beanutils;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestBean {
    public static void main(String[] args) {
        Student successResult = new Student(200, "success", "data");
        Student errorResult = new Student(null, "error", null);
        System.out.println(successResult + " " + errorResult);
        // BeanUtil.copyProperties(successResult, errorResult);
        // System.out.println(errorResult);

        MergeUtil.mergeOverride(successResult, errorResult);
        System.out.println(errorResult);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Student {
    private Integer code;
    private String message;
    private String data;
}
