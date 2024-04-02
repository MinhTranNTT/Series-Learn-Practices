package com.neet.neetdesign.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String age;

    @JsonProperty("class")
    private String className;

    // @Override
    // public String toString() {
    //     return "Student{" +
    //             "id=" + id +
    //             ", name='" + name + '\'' +
    //             ", age='" + age + '\'' +
    //             ", className='" + className + '\'' +
    //             '}';
    // }
}
