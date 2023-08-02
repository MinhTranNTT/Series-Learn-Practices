package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)    // identity increment
    private Long id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(select = false)     // not select
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email")
    private String email;

    @TableField(exist = false)  // not exist in table
    private String address;
}
