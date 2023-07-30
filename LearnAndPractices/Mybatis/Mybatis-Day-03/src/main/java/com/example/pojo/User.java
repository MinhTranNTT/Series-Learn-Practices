package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId("ID")
    private Long id;
    @TableField("NAME")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
}
