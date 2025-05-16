package org.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.blog.dto.CategoryDto;
import org.blog.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> getAllCategory();
    Category getCategoryById(Long id);
    int createCategory(CategoryDto categoryDto);
    Category getCategoryByName(String name);
}
