package com.procesos.parcial.service;

import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.dto.Response;

import java.util.List;

public interface AuthorService {

    Response createAuthor(Author author);
    Response updateAuthor(Author author, Long id);
    Response getAuthorById(Long id);
    Response deleteAuthor(Long id);
    Response findAllAuthors();

}