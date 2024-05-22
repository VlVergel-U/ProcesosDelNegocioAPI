package com.procesos.parcial.service;

import com.procesos.parcial.model.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);
    Author updateAuthor(Author author, Long id);
    Author getAuthorById(Long id);
    void deleteAuthor(Long id);
    List<Author> findAllAuthors();

}