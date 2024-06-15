package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.Book;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.dto.ResponseUtil;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.model.enums.SuccessMessage;
import com.procesos.parcial.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceOp implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ResponseUtil response;

    @Override
    public Response createAuthor(Author author) {
        Optional<Author> findByUniqueCode = authorRepository.findByCode(author.getCode());
        if (findByUniqueCode.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.AUTHOR_ALREADY_EXISTS.getMessage());
        }
        authorRepository.save(author);
        return response.generateResponse(SuccessMessage.AUTHOR_CREATED.getMessage(), author, String.valueOf(HttpStatus.CREATED));
    }

    @Override
    public Response updateAuthor(Author updatedAuthor, Long id) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isEmpty()) {
            throw new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND.getMessage());
        }
        Author existingAuthorEntity = existingAuthor.get();

        existingAuthorEntity.setFirstName(updatedAuthor.getFirstName());
        existingAuthorEntity.setSecondName(updatedAuthor.getSecondName());
        existingAuthorEntity.setFirstLastName(updatedAuthor.getFirstLastName());
        existingAuthorEntity.setSecondLastName(updatedAuthor.getSecondLastName());
        existingAuthorEntity.setBirthDate(updatedAuthor.getBirthDate());
        authorRepository.save(existingAuthorEntity);
        return response.generateResponse(SuccessMessage.AUTHOR_UPDATED.getMessage(), existingAuthorEntity, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.AUTHOR_FOUND.getMessage(), author, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND.getMessage());
        }
        authorRepository.deleteById(id);
        return response.generateResponse(SuccessMessage.AUTHOR_DELETED.getMessage(), author, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response findAllAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new NotFoundException(ErrorMessage.AUTHORS_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.AUTHORS_FOUND.getMessage(), authors, String.valueOf(HttpStatus.OK));
    }
}
