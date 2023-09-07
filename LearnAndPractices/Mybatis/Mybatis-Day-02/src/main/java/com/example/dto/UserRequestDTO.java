package com.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UserRequestDTO {
    private String name;
    private int age;
    private List<MultipartFile> images;

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", images=" + images +
                '}';
    }
}
