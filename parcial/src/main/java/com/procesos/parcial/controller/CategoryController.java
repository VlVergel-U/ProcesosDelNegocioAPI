package com.procesos.parcial.controller;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/category")
    public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        @PostMapping
        public Category createCategory(@RequestBody Category category) {
            return categoryService.createCategory(category);
        }

        @GetMapping("/{id}")
        public Category getCategoryById(@PathVariable Long id) {
            return categoryService.getCategoryById(id);
        }

        @PutMapping("/{id}")
        public Category updateCategory(@RequestBody Category category, @PathVariable Long id) {
            return categoryService.updateCategory(category, id);
        }

        @GetMapping
        public List<Category> allCategories() {
            return categoryService.findAllCategories();
        }
    }


