package org.blog.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blog.dto.CategoryDto;
import org.blog.entity.Category;
import org.blog.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
public class HelloController {
    private final CategoryService categoryService;


    @GetMapping("/getHello")
    public String getHello() {
        return "Hello World";
    }

    @GetMapping("/getHello/{id}")
    public String getHelloById(@PathVariable("id") Long id) {
        return "Hello World " + id;
    }

    @DeleteMapping("/getHello")
    public String deleteHelloWord(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "id", required = false) Long id) {
        return "Delete user " + name + " with ID is " + id;
    }

    @GetMapping("/getCategory")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        Category category = Optional.ofNullable(categoryService.getCategoryById(id))
                .orElseThrow(() -> new RuntimeException("Category not found ID = " + id));
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/category")
    public String createCategory(@RequestBody CategoryDto category) {
        Category categoryExist = categoryService.getCategoryByName(category.getCategoryName());
        if (categoryExist != null) {
            return "Category existing. Create failure";
        }

        int categoryCreated = categoryService.createCategory(category);
        if (category.getId() <= 0) {
            return "Create failure. DB Error";
        }
        return "Created successfully Category with ID = " + category.getId();
    }
}
