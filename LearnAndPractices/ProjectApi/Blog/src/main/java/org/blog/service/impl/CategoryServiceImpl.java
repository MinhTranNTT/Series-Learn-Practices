package org.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blog.dto.CategoryDto;
import org.blog.entity.Category;
import org.blog.mapper.CategoryMapper;
import org.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> getAllCategory() {
        return baseMapper.getAllCategory();
    }

    @Override
    public Category getCategoryById(Long id) {
        return baseMapper.getCategoryById(id);
    }

    @Override
    public int createCategory(CategoryDto categoryDto) {
        return baseMapper.createCategory(categoryDto);
    }

    @Override
    public Category getCategoryByName(String name) {
        return baseMapper.getCategoryByName(name);
    }
}
