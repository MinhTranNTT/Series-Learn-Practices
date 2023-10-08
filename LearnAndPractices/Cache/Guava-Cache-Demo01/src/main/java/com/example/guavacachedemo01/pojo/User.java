package com.example.guavacachedemo01.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String firstname;

    @JsonView(View.Summary.class)
    private String lastname;

    private String email;
    private String address;
    private String postalCode;
    private String city;
    private String country;
}
