package com.procesos.parcial.service;

import com.procesos.parcial.model.Book;
import com.procesos.parcial.model.dto.Response;

import java.util.List;

public interface BookService {

    Response createBook (Book book);
    Response updateBook(Book book, Long id);
    Response getBookById(Long id);
    Response deleteBook(Long id);
    Response findAllBooks();


}
