package com.procesos.parcial.service;
import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.enums.CategoryEnum;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Category category, Long id);
    Category getCategoryById(Long id);
    List<Category> findAllCategories();
}