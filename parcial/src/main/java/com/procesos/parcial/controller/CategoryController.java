package com.procesos.parcial.controller;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.dto.Request;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Response> createCategory(@Valid  @RequestBody Request<Category> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request.getObject()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCategory(@Valid @RequestBody Request<Category> request, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(categoryService.updateCategory(request.getObject(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

    @GetMapping
    public ResponseEntity<Response> allCategories() {
        return ResponseEntity.ok().body(categoryService.findAllCategories());
    }
}
