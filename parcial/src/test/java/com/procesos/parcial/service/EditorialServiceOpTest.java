package com.procesos.parcial.service;

import com.procesos.parcial.model.Address;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.repository.EditorialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EditorialServiceOpTest {

    @InjectMocks
    private EditorialServiceOp editorialServiceOp;

    @Mock
    private EditorialRepository editorialRepository;

    private Editorial editorial;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        editorial = new Editorial();
        editorial.setId(1L);
        editorial.setName("Editorial XYZ");
        editorial.setCity("Bogota");

        Address address = new Address();
        address.setId(1L);
        address.setAvenue("Holis");
        address.setStreet("Holis");
        address.setNeighborhood("Holis");
        address.setPostalCode("1123232");
        editorial.setAddress(address);
        editorial.setPhoneNumber("1234567890");
        editorial.setCountry("Colombia");
    }

    @Test
    void createEditorial() {
        // Test implementation
    }

    @Test
    void updateEditorial() {
        // Test implementation
    }

    @Test
    void getEditorialById() {
        // Test implementation
    }

    @Test
    void deleteEditorial() {
        // Test implementation
    }

    @Test
    void findAllEditorials() {
        // Test implementation
    }
}
