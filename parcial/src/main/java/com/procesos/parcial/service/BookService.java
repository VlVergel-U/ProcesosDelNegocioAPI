package com.procesos.parcial.service;

import com.procesos.parcial.model.Book;

import java.util.List;

public interface BookService {

    Book createBook (Book book);
    Book updateBook(Book book, Long id);
    Book getBookById(Long id);
    List<Book> findAllBooks();

}
