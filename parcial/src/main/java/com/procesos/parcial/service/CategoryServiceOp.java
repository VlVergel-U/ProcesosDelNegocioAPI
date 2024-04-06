package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class CategoryServiceOp implements CategoryService {

        private final CategoryRepository categoryRepository;

    public CategoryServiceOp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category categoryUpdated, Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category categoryBD = categoryOptional.get();
            categoryBD.setCategory(categoryUpdated.getCategory());
            return categoryRepository.save(categoryBD);
        }
        return null;
    }


    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> categoryBD = categoryRepository.findById(id);
        return categoryBD.orElse(null);
    }
        @Override
        public List<Category> findAllCategories() {
            return (List<Category>) categoryRepository.findAll();
        }
    }



