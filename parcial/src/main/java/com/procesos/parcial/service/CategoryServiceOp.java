package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.enums.CategoryEnum;
import com.procesos.parcial.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CategoryServiceOp implements CategoryService {

        @Autowired
        private CategoryRepository categoryRepository;


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category categoryUpdated, Long id) {
        Category categoryBD = categoryRepository.findById(id).orElse(null);
        if (categoryBD != null) {
            categoryBD.setCategory(categoryUpdated.getCategory());
            return categoryRepository.save(categoryUpdated);
        }
        return null;
    }

    @Override
        public Category getCategoryById(Long id) {
            return categoryRepository.findById(id).orElse(null);
        }

        @Override
        public List<Category> findAllCategories() {
            return (List<Category>) categoryRepository.findAll();
        }
    }



