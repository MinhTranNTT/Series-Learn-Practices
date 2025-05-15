package org.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.blog.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> getAllCategory();
}
