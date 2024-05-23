package com.procesos.parcial.service;

import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.Category;
import com.procesos.parcial.repository.AuthorRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

public class CategoryServiceOpTest {

    @InjectMocks
    private CategoryServiceOp categoryServiceOp;

    @Mock
    private CategoryServiceOp categoryRepository;
    private Category category;


    @BeforeEach
    void setUp() {
        // Inicialización de los mocks
        MockitoAnnotations.openMocks(this);

        // Configuración de la categoría de prueba
        category = new Category();
        category.setId(1L);
        category.setName("Ficcion");

    }

    @Test
    public void createCategory() {
    }

    @Test
    public void updateCategory() {
    }

    @Test
    public void getCategoryById() {
    }

    @Test
    public void deleteCategory() {
    }

    @Test
    public void findAllCategories() {
    }
}