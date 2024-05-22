package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.DuplicateAutorException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.*;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    private AutorRepository autorRepository;

    @Override
    public Book createBook(Book book) {
        Optional<Book> findByName = bookRepository.findByName(book.getName());
        if (findByName.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.BOOK_ALREADY_EXISTS.getMessage());
        }
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

        // Verificar si los autores existen
        List<Autor> authorsToSave = new ArrayList<>();
        for (Autor author : book.getAuthors()) {
            Optional<Autor> autorOptional = autorRepository.findByUniqueCode(author.getUniqueCode());
            if (autorOptional.isPresent()) {
                authorsToSave.add(autorOptional.get());
            } else {
                try {
                    Autor savedAutor = autorRepository.save(author);
                    authorsToSave.add(savedAutor);
                } catch (DataIntegrityViolationException e) {
                    // Manejar la excepción aquí
                    throw new DuplicateAutorException("Autor with unique code '" + author.getUniqueCode() + "' already exists");
                }
            }
        }
        book.setAuthors(new HashSet<>(authorsToSave));



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

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book updatedBook, Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }

        existingBook.get().setName(updatedBook.getName());
        existingBook.get().setDescription(updatedBook.getDescription());
        existingBook.get().setAuthors(updatedBook.getAuthors());
        existingBook.get().setEditionNumber(updatedBook.getEditionNumber());
        existingBook.get().setPrice(updatedBook.getPrice());
        existingBook.get().setPublicationDate(updatedBook.getPublicationDate());
        existingBook.get().setLanguage(updatedBook.getLanguage());
        existingBook.get().setCategory(updatedBook.getCategory());
        existingBook.get().setEditorial(updatedBook.getEditorial());
        return bookRepository.save(existingBook.get());
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }
        return book.get();
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
}