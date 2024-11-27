package com.pet.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("ums_sys_user")
public class UmsSysUser implements Serializable {
    @TableId
    //使用包装类。为什么呢？
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    //用Integer
    private Integer sex;
    private String avatar;
    private String password;
    private Integer status;
    private String creator;
    private String updater;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;
    //逻辑删除，MyBatis-PUs默认0是未删除，1是已删除
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private List<UmsRole> roleList = new ArrayList<>();

    @TableField(exist = false)
    private List<String> perms = new ArrayList<>();

}
