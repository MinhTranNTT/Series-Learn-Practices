package com.example.mybatisday04.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tbl_employee")
public class Employee {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String lastName;
    private String email;
    private Integer age;
    private int gender;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtModified;

    @TableLogic
    @TableField(value = "is_deleted")
    private boolean deleted;
}
