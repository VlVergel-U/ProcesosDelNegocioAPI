package com.procesos.parcial.service;

import com.procesos.parcial.model.Book;
import com.procesos.parcial.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceOp  implements  BookService{

    @Autowired
    private BookRepository bookRepository;

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
        bookBD.get().setName(bookUpdated.getName());
        bookBD.get().setCategory(bookUpdated.getCategory());
        bookBD.get().setAutor(bookUpdated.getAutor());
        bookBD.get().setLanguage(bookUpdated.getLanguage());
        bookBD.get().setPrice(bookUpdated.getPrice());
        bookBD.get().setEditionNumber(bookUpdated.getEditionNumber());
        bookBD.get().setDescription(bookUpdated.getDescription());
        bookBD.get().setPublicationDate(bookUpdated.getPublicationDate());
        bookBD.get().setEditorials(bookUpdated.getEditorials());
        return bookRepository.save(bookBD.get());
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> bookBD = bookRepository.findById(id);
        if (bookBD.isEmpty()) {
            return null;
        }
        return bookBD.get();
    }

    @Override
    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
}