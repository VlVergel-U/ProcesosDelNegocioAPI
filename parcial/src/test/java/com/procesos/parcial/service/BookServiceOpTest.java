package com.procesos.parcial.service;

import com.procesos.parcial.model.*;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.dto.ResponseUtil;
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
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Mock
    private ResponseUtil responseUtil; // Inyectamos ResponseUtil

    private Book book;
    private Category category;
    private Editorial editorial;

    @BeforeEach
    void setUp() {
        // Inicialización de los mocks
        MockitoAnnotations.openMocks(this);

        // Configuración de la categoría de prueba
        category = new Category();
        category.setId(1L);
        category.setName("Ficcion");

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

        // Configuración del autor de prueba
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("Gabriel");
        author.setFirstLastName("García");
        author.setSecondLastName("Márquez");
        LocalDate localDate = LocalDate.of(1981, Month.JANUARY, 1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        author.setBirthDate(date);

        Set<Author> authors = new HashSet<>();
        authors.add(author);

        // Configuración del libro de prueba
        book = new Book();
        book.setId(1L);
        book.setName("Crónica de una muerte anunciada");
        book.setDescription("Es un libro que no tengo ni idea la verdad pero muy bueno");
        book.setCategory(category); // Set the category directly
        book.setAuthors(authors);
        book.setEditorial(editorial);
        book.setEditionNumber("1st edition");
        book.setPrice(19.99);
        book.setPublicationDate(localDate);
        book.setLanguage(Language.SPANISH);
    }

    @Test
    void findAll() {
        // Configuración del mock para retornar una lista de libros
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        // Mock de ResponseUtil
        Response mockResponse = Response.builder()
                .date(LocalDate.now())
                .message(Collections.singletonList("Success"))
                .statusCode(HttpStatus.OK.toString())
                .data(Collections.singletonList(book))
                .build();
        when(responseUtil.generateResponse(anyString(), any(), anyString())).thenReturn(mockResponse);

        // Llamada al método a probar
        Response response = bookServiceOp.findAllBooks();

        // Verificación del resultado
        assertEquals(HttpStatus.OK.toString(), response.getStatusCode());
        List<Book> books = (List<Book>) response.getData(); // Cast a List<Book>
        assertEquals(1, books.size()); // Verificar que se haya retornado un libro
        assertEquals(book, books.get(0)); // Verificar que se haya retornado el libro correcto
    }

    @Test
    void createBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));
        when(editorialRepository.findByName(anyString())).thenReturn(Optional.of(editorial));

        // Mock de ResponseUtil
        Response mockResponse = Response.builder()
                .date(LocalDate.now())
                .message(Collections.singletonList("Success"))
                .statusCode(HttpStatus.CREATED.toString())
                .data(book)
                .build();
        when(responseUtil.generateResponse(anyString(), any(), anyString())).thenReturn(mockResponse);

        Response response = bookServiceOp.createBook(book);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.toString(), response.getStatusCode());
        Book createdBook = (Book) response.getData();
        assertEquals(book.getName(), createdBook.getName());
        assertEquals(book.getDescription(), createdBook.getDescription());
        assertEquals(book.getCategory(), createdBook.getCategory());
        assertEquals(book.getAuthors(), createdBook.getAuthors());
        assertEquals(book.getEditorial(), createdBook.getEditorial());
        assertEquals(book.getEditionNumber(), createdBook.getEditionNumber());
        assertEquals(book.getPrice(), createdBook.getPrice());
        assertEquals(book.getPublicationDate(), createdBook.getPublicationDate());
        assertEquals(book.getLanguage(), createdBook.getLanguage());

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void updateBook() {

    }


    @Test
    void getBookById() {
        // Configuración del mock para retornar el libro
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // Mock de ResponseUtil
        Response mockResponse = Response.builder()
                .date(LocalDate.now())
                .message(Collections.singletonList("Success"))
                .statusCode(HttpStatus.OK.toString())
                .data(book)
                .build();
        when(responseUtil.generateResponse(anyString(), any(), anyString())).thenReturn(mockResponse);

        // Llamada al método a probar
        Response response = bookServiceOp.getBookById(book.getId());

        // Verificación del resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK.toString(), response.getStatusCode());
        Book returnedBook = (Book) response.getData(); // Cast a Book
        assertEquals(book, returnedBook);
    }

    @Test
    void deleteBook() {
        // Configuración del mock para retornar el libro cuando findById es llamado con el id correcto
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // Mock de ResponseUtil
        Response mockResponse = Response.builder()
                .date(LocalDate.now())
                .message(Collections.singletonList("Success"))
                .statusCode(HttpStatus.OK.toString())
                .data(book)
                .build();
        when(responseUtil.generateResponse(anyString(), any(), anyString())).thenReturn(mockResponse);

        // Llama a deleteBook con el id correcto del libro
        Response response = bookServiceOp.deleteBook(book.getId());

        // Verifica que el método deleteById sea llamado con el id correcto del libro
        verify(bookRepository, times(1)).deleteById(book.getId());
        assertEquals(HttpStatus.OK.toString(), response.getStatusCode());
    }
}
