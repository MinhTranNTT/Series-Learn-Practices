package com.neet.neetdesign.miscellaneous.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentBad {
    private int id;
    private String name;
    private String course;

    @JsonProperty("hobby_course")
    private String hobbyCourse;

}
