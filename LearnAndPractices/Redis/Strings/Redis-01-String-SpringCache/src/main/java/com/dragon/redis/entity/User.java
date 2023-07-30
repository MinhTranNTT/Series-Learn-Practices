package com.dragon.redis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String username;
    private String password;
    private Byte sex;
    private Byte deleted;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "create_time")
    private Date createTime;

}
