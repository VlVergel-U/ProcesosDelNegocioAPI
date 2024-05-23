package com.procesos.parcial.service;

import com.procesos.parcial.model.Address;
import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.repository.AuthorRepository;
import com.procesos.parcial.repository.EditorialRepository;
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

public class EditorialServiceOpTest {

    @InjectMocks
    private EditorialServiceOp editorialServiceOp;

    @Mock
    private EditorialRepository editorialRepository;
    private Editorial editorial;

    @BeforeEach
    void setUp() {
        // Inicialización de los mocks
        MockitoAnnotations.openMocks(this);

        // Configuración del editorial de prueba
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
    public void createEditorial() {
    }

    @Test
    public void updateEditorial() {
    }

    @Test
    public void getEditorialById() {
    }

    @Test
    public void deleteEditorial() {
    }

    @Test
    public void findAllEditorials() {
    }
}