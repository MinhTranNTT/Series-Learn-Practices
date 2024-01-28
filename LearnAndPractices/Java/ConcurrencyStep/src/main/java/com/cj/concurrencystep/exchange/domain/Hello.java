package com.cj.concurrencystep.exchange.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hello {
    @JsonProperty("name")
    private String name;

    @JsonProperty("images")
    private List<String> imageList;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("orderName")
    private String orderName;

    @JsonProperty("stock")
    private Integer stock;
}
