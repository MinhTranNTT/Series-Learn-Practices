package org.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.blog.dto.CategoryDto;
import org.blog.entity.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getAllCategory();
    Category getCategoryById(@Param("id") Long id);
    int createCategory(@Param("category") CategoryDto categoryDto);
    Category getCategoryByName(@Param("name") String name);
}
