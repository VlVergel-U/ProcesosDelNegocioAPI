package com.procesos.parcial.service;

import com.procesos.parcial.model.*;
import com.procesos.parcial.model.enums.Language;
import com.procesos.parcial.repository.AuthorRepository;
import com.procesos.parcial.repository.BookRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

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
    public void createAuthor() {
    }

    @Test
    public void updateAuthor() {
    }

    @Test
    public void getAuthorById() {
    }

    @Test
    public void deleteAuthor() {
    }

    @Test
    public void findAllAuthors() {
    }
}