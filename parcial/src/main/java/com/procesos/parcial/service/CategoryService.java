package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.dto.Response;

import java.util.List;

public interface CategoryService {
    Response createCategory(Category category);
    Response updateCategory(Category category, Long id);
    Response getCategoryById(Long id);
    Response deleteCategory(Long id);
    Response findAllCategories();
}
