package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Category category, Long id);
    Category getCategoryById(Long id);
    void deleteCategory(Long id);
    List<Category> findAllCategories();
}
