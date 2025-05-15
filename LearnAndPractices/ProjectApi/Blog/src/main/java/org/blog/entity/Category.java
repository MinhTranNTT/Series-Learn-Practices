package org.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "category_name")
    private String categoryName;
    @TableField(value = "category_code")
    private String categoryCode;

}
