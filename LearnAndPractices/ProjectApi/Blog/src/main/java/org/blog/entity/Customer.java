package org.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName("customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @TableId(type = IdType.AUTO)
    private long id;
    private String email;
    private String pwd;
    @TableField(value = "role")
    private String role;

    private List<String> authorities;


}