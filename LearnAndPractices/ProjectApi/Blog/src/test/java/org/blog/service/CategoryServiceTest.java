package org.blog.service;

import org.blog.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        categories.forEach(System.out::println);
    }
}