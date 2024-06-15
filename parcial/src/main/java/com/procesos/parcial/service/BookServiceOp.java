package com.procesos.parcial.service;

import com.procesos.parcial.Validations.AuthorValidator;
import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.DuplicateAuthorException;
import com.procesos.parcial.exceptions.InvalidAuthorException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.*;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.dto.ResponseUtil;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.model.enums.SuccessMessage;
import com.procesos.parcial.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
@Transactional
public class BookServiceOp implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceOp.class);
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ResponseUtil response;

    @Override
    public Response createBook(Book book) {
        Optional<Book> findByName = bookRepository.findByName(book.getName());
        if (findByName.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.BOOK_ALREADY_EXISTS.getMessage());
        }

        // Verificar si los autores existen
        Set<Author> authorsToSave = new HashSet<>();
        for (Author author : book.getAuthors()) {
            if (author.getId() == null) {
                // El autor no existe, crear uno nuevo
                AuthorValidator authorValidator = new AuthorValidator();
                if (!authorValidator.isValid(author)) {
                    throw new InvalidAuthorException("Los campos de autor no son válidos");
                }
                try {
                    Author savedAutor = authorRepository.save(author);
                    authorsToSave.add(savedAutor);
                } catch (DataIntegrityViolationException e) {
                    throw new DuplicateAuthorException("Autor with unique code '" + author.getCode() + "' already exists");
                }
            } else {
                // El autor ya existe, buscarlo en la base de datos
                Optional<Author> autorOptional = authorRepository.findById(author.getId());
                if (autorOptional.isPresent()) {
                    authorsToSave.add(autorOptional.get());
                } else {
                    // El autor no existe, crear uno nuevo
                    AuthorValidator authorValidator = new AuthorValidator();
                    if (!authorValidator.isValid(author)) {
                        throw new InvalidAuthorException("Los campos de autor no son válidos");
                    }
                    try {
                        Author savedAutor = authorRepository.save(author);
                        authorsToSave.add(savedAutor);
                    } catch (DataIntegrityViolationException e) {
                        throw new DuplicateAuthorException("Autor with unique code '" + author.getCode() + "' already exists");
                    }
                }
            }
        }

        book.setAuthors(authorsToSave);


//verificar si categoria existe
        Optional<Category> categoryOptional = categoryRepository.findByName(book.getCategory().getName());
        if (categoryOptional.isPresent()) {
            book.setCategory(categoryOptional.get());
        } else {
            Category savedCategory = categoryRepository.save(book.getCategory());
            book.setCategory(savedCategory);
        }

//verificar si editorial existe
        Optional<Editorial> editorialOptional = editorialRepository.findByName(book.getEditorial().getName());
        if (editorialOptional.isPresent()) {
            book.setEditorial(editorialOptional.get());
        } else {
            Editorial savedEditorial = editorialRepository.save(book.getEditorial());
            book.setEditorial(savedEditorial);
        }

        // Devolver mensaje al cliente
        if (categoryOptional.isPresent()) {
            logger.info("La categoría {} ya existe, se utilizó la existente.", book.getCategory().getName());
        } else {
            logger.info("Se creó la nueva categoría {}.", book.getCategory().getName());
        }

        if (editorialOptional.isPresent()) {
            logger.info("La editorial {} ya existe, se utilizó la existente.", book.getEditorial().getName());
        } else {
            logger.info("Se creó la nueva editorial {}.", book.getEditorial().getName());
        }

        bookRepository.save(book);
        return response.generateResponse(SuccessMessage.BOOK_CREATED.getMessage(), book, String.valueOf(HttpStatus.CREATED));
    }

    @Override
    public Response updateBook(Book updatedBook, Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }

        Book existingBookEntity = existingBook.get();

        existingBookEntity.setName(updatedBook.getName());
        existingBookEntity.setDescription(updatedBook.getDescription());
        existingBookEntity.setEditionNumber(updatedBook.getEditionNumber());
        existingBookEntity.setPrice(updatedBook.getPrice());
        existingBookEntity.setPublicationDate(updatedBook.getPublicationDate());
        existingBookEntity.setLanguage(updatedBook.getLanguage());

        // Buscar categoría existente
        Optional<Category> categoryOptional = categoryRepository.findByName(updatedBook.getCategory().getName());
        if (categoryOptional.isPresent()) {
            existingBookEntity.setCategory(categoryOptional.get());
        } else {
            Category newCategory = categoryRepository.save(updatedBook.getCategory());
            existingBookEntity.setCategory(newCategory);
        }


        // Buscar editorial existente
        Optional<Editorial> editorialOptional = editorialRepository.findByName(updatedBook.getEditorial().getName());
        if (editorialOptional.isPresent()) {
            existingBookEntity.setEditorial(editorialOptional.get());
        } else {
            Editorial newEditorial = editorialRepository.save(updatedBook.getEditorial());
            existingBookEntity.setEditorial(newEditorial);
        }

        // Buscar autores existentes
        List<Author> authorsToSave = new ArrayList<>();
        for (Author author : updatedBook.getAuthors()) {
            Optional<Author> authorOptional = authorRepository.findByCode(author.getCode());
            if (authorOptional.isPresent()) {
                authorsToSave.add(authorOptional.get());
            } else {
                throw new DuplicateAuthorException("Autor with unique code " + author.getCode() + " already exists.");
            }
        }

        existingBookEntity.setAuthors(new HashSet<>(authorsToSave));

        bookRepository.save(existingBookEntity);
        return response.generateResponse(SuccessMessage.BOOK_UPDATED.getMessage(), existingBookEntity, String.valueOf(HttpStatus.OK));

    }

    @Override
    public Response getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.BOOK_FOUND.getMessage(), book, String.valueOf(HttpStatus.OK));

    }

    @Override
    public Response deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }
        bookRepository.deleteById(id);
        return response.generateResponse(SuccessMessage.BOOK_DELETED.getMessage(), book, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response findAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOKS_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.BOOKS_FOUND.getMessage(), books, String.valueOf(HttpStatus.OK));
    }

}