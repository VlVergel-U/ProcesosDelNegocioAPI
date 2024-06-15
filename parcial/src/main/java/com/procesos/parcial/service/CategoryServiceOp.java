package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.dto.ResponseUtil;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.model.enums.SuccessMessage;
import com.procesos.parcial.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceOp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResponseUtil response;

    @Override
    public Response createCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_ALREADY_EXISTS.getMessage());
        }
        categoryRepository.save(category);
        return response.generateResponse(SuccessMessage.CATEGORY_CREATED.getMessage(), category, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response updateCategory(Category categoryUpdated, Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }
        Category category = categoryOptional.get();
        category.setName(categoryUpdated.getName());
        try {
            categoryRepository.save(category);
            return response.generateResponse(SuccessMessage.CATEGORY_UPDATED.getMessage(), category, String.valueOf(HttpStatus.OK));

        } catch (Exception e) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_HASNT_BEEN_UPDATED.getMessage());
        }
    }

    @Override
    public Response getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw  new AlreadyExistsException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.CATEGORY_FOUND.getMessage(), category, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        } else {
            try {
                categoryRepository.deleteById(id);
                return response.generateResponse(SuccessMessage.CATEGORY_DELETED.getMessage(), category, String.valueOf(HttpStatus.OK));
            } catch (Exception e) {
                throw new AlreadyExistsException(ErrorMessage.CATEGORY_HASNT_BEEN_DELETED.getMessage());
            }
        }
    }

    @Override
    public Response findAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.CATEGORIES_NOT_FOUND.getMessage());
        }

        return response.generateResponse(SuccessMessage.CATEGORIES_FOUND.getMessage(), categories, String.valueOf(HttpStatus.OK));

    }
}
