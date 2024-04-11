package com.procesos.parcial.service;

import com.procesos.parcial.model.Book;
import com.procesos.parcial.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceOp implements BookService {

    private final BookRepository bookRepository;

    public BookServiceOp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book bookUpdated, Long id) {
        Optional<Book> bookBD = bookRepository.findById(id);
        if (bookBD.isEmpty()) {
            return null;
        }
        Book existingBook = bookBD.get();
        existingBook.setName(bookUpdated.getName());
        existingBook.setCategory(bookUpdated.getCategory());
        existingBook.setAutor(bookUpdated.getAutor());
        existingBook.setLanguage(bookUpdated.getLanguage());
        existingBook.setPrice(bookUpdated.getPrice());
        existingBook.setEditionNumber(bookUpdated.getEditionNumber());
        existingBook.setDescription(bookUpdated.getDescription());
        existingBook.setPublicationDate(bookUpdated.getPublicationDate());
        existingBook.setEditorials(bookUpdated.getEditorials());
        return bookRepository.save(existingBook);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> bookBD = bookRepository.findById(id);
        return bookBD.orElse(null);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

}
