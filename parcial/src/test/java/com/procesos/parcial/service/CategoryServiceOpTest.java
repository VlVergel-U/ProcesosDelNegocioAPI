package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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