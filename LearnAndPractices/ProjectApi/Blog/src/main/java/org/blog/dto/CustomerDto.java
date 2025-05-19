package org.blog.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private long id;
    private String email;
    private String pwd;
    private String role;

}
