package org.blog.service;

import org.blog.entity.Category;
import org.blog.entity.Customer;
import org.blog.mapper.CategoryMapper;
import org.blog.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryServiceTest {
    @Autowired private CategoryService categoryService;
    @Autowired private CategoryMapper categoryMapper;
    @Autowired private CustomerMapper customerMapper;

    @Test
    public void getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        categories.forEach(System.out::println);
    }

    @Test
    public void getCategoryById() {
        Category category = categoryService.getCategoryById(1L);
        System.out.println(category);
    }

    @Test
    public void getAllCategoryByMapper() {
        List<Category> categories = categoryMapper.getAllCategory();
        categories.forEach(System.out::println);
    }

    @Test
    public void getCustomerByEmail() {
        Customer customer = customerMapper.getCustomerByEmail("user@example.com");
        System.out.println("customer = " + customer);
    }
}