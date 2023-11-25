package com.practices.colllections.trying;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentScore {
    private String studentName;
    private String subject;
    private int score;
}
