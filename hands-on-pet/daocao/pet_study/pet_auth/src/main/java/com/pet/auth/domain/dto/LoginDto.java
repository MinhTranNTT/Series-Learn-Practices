package com.pet.auth.domain.dto;


import lombok.Data;

@Data
public class LoginDto {
    private String account;
    private String password;
    private Integer rememberMe;
}
