package com.pet.common.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RouterVO {
    private Long id;
    private Long parentId;
    private String menuName;
    private String path;
    private String perms;
    private String componentPath;
    private String icon;
    private Integer menuType; // 0是目录，1是菜单，2是按钮
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    // 存放子路由
    private List<RouterVO> children = new ArrayList<>();
}
