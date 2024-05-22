package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceOp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent()) {
            throw  new AlreadyExistsException(ErrorMessage.CATEGORY_ALREADY_EXISTS.getMessage());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category categoryUpdated, Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }
        Category categoryBD = categoryOptional.get();
        categoryBD.setName(categoryUpdated.getName());
        try {
            return categoryRepository.save(categoryBD);
        } catch (Exception e) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_HASNT_BEEN_UPDATED.getMessage());
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> categoryBD = categoryRepository.findById(id);
        if (categoryBD.isEmpty()) {
            throw  new AlreadyExistsException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }
        return categoryBD.get();
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> categoryBD = categoryRepository.findById(id);
        if (categoryBD.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        } else {
            try {
                categoryRepository.deleteById(id);
            } catch (Exception e) {
                throw new AlreadyExistsException(ErrorMessage.CATEGORY_HASNT_BEEN_DELETED.getMessage());
            }
        }
    }

    @Override
    public List<Category> findAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
}
