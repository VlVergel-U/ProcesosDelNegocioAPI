package com.procesos.parcial.service;

import com.procesos.parcial.model.Author;
import com.procesos.parcial.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class AuthorServiceOpTest {

    @InjectMocks
    private AuthorServiceOp authorServiceOp;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        // Inicialización de los mocks
        MockitoAnnotations.openMocks(this);

        // Configuración del autor de prueba
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("Gabriel");
        author.setFirstLastName("García");
        author.setSecondLastName("Márquez");
        LocalDate localDate = LocalDate.of(1981, Month.JANUARY, 1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        author.setBirthDate(date);
    }

    @Test
    void createAuthor() {
        // Test implementation
    }

    @Test
    void updateAuthor() {
        // Test implementation
    }

    @Test
    void getAuthorById() {
        // Test implementation
    }

    @Test
    void deleteAuthor() {
        // Test implementation
    }

    @Test
    void findAllAuthors() {
        // Test implementation
    }
}
