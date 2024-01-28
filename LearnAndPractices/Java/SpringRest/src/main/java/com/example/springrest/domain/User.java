package com.example.springrest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id","userId","title","completed" })
// @ToString
public class User {
    @JsonProperty("id")
    private int id;
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("completed")
    private Boolean completed;

}
