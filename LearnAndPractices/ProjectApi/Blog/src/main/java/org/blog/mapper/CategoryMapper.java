package org.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.blog.entity.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getAllCategory();
}
