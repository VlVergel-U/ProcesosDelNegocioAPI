package com.procesos.parcial.service;

import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.Book;
import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.enums.Language;
import com.procesos.parcial.repository.AuthorRepository;
import com.procesos.parcial.repository.BookRepository;
import com.procesos.parcial.repository.CategoryRepository;
import com.procesos.parcial.repository.EditorialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceOpTest {

    @InjectMocks
    private BookServiceOp bookServiceOp;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private EditorialRepository editorialRepository;

    @Mock
    private AuthorRepository authorRepository;

    private Book book;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll(){
        // Configuración del mock para retornar una lista de libros
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        // Llamada al método a probar
        List<Book> books = bookServiceOp.findAllBooks();

        // Verificación del resultado
        assertEquals(1, books.size()); // Verificar que se haya retornado un libro
        assertEquals(book, books.get(0)); // Verificar que se haya retornado el libro correcto
    }

    @Test
    void createBook() {
        bookServiceOp= new BookServiceOp();
        book = new Book();
        book.setId(1L);
        book.setName("Crónica de una muerte anunciada");
        book.setDescription("Es un libro que no tengo ni idea la vdd pero muy bueno");
        Category category = new Category();
        category.setId(1L);
        category.setName("Ficcion");
        book.setCategory(category);
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("Gabriel");
        author.setFirstLastName("García");
        author.setSecondLastName("Márquez");
        Set<Author> autores = new HashSet<>();
        autores.add(author);
        book.setAuthors(autores);
        Editorial editorial = new Editorial();
        editorial.setId(1L);
        editorial.setName("Editorial XYZ");
        book.setEditorial(editorial);
        book.setEditionNumber("1st edition");
        book.setPrice(19.99);
        book.setPublicationDate(LocalDate.of(1981, Month.JANUARY, 1));
        book.setLanguage(Language.SPANISH);
    }

    @Test
    void updateBook() {
        // Aquí podrías implementar pruebas para el método updateBook
    }

    @Test
    void getBookById() {
        // Aquí podrías implementar pruebas para el método getBookById
    }

    @Test
    void deleteBook() {
        // Aquí podrías implementar pruebas para el método deleteBook
    }

    @Test
    void findAllBooks() {
        // Aquí podrías implementar pruebas para el método findAllBooks
    }


}