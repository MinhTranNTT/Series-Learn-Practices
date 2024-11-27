package com.pet.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("ums_menu")
@Data
public class UmsMenu {
    @TableId
    private Long id;
    private Long parentId;
    private String menuName;
    private String path;
    private String componentPath;
    private String perms;
    private String icon;
    private Integer menuType;
    private Integer sort;
    private Integer status;
    private String creator;
    private String updater;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;
    //逻辑删除，MyBatis-P1Us默认0是未删除，1是已删除
    private Integer deleted;
}
