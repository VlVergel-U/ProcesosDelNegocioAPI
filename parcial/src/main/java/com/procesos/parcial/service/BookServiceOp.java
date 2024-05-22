package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.Address;
import com.procesos.parcial.model.Book;
import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.repository.AddressRepository;
import com.procesos.parcial.repository.BookRepository;
import com.procesos.parcial.repository.CategoryRepository;
import com.procesos.parcial.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceOp implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Book createBook(Book book) {
        Optional<Book> findByName = bookRepository.findByName(book.getName());
        if (findByName.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.BOOK_ALREADY_EXISTS.getMessage());
        }

        if (book.getEditorial() != null && book.getEditorial().getAddress() != null) {
            Address address = book.getEditorial().getAddress();
            Address savedAddress = addressRepository.save(address);
            book.getEditorial().setAddress(savedAddress);
        }

        if (book.getEditorial() != null) {
            Optional<Editorial> existingEditorial = editorialRepository.findByName(book.getEditorial().getName());
            if (existingEditorial.isPresent()) {
                book.setEditorial(existingEditorial.get());
            } else {
                Editorial savedEditorial = editorialRepository.save(book.getEditorial());
                book.setEditorial(savedEditorial);
            }
        }

        if (book.getCategory() != null) {
            Optional<Category> existingCategory = categoryRepository.findById(book.getCategory().getId());
            if (existingCategory.isEmpty()) {
                // Category doesn't exist, save it
                Category savedCategory = categoryRepository.save(book.getCategory());
                // Set the saved category to the book
                book.setCategory(savedCategory);
            } else {
                book.setCategory(existingCategory.get());
            }
        }

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book, Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isEmpty()) {
            throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND.getMessage());
        }

        // Update the book fields as needed
        // ...

        return bookRepository.save(book);
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