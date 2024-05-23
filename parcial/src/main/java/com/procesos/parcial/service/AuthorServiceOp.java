package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceOp implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        Optional<Author> findByUniqueCode = authorRepository.findByCode(author.getCode());
        if (findByUniqueCode.isPresent()) {
            throw new AlreadyExistsException(ErrorMessage.AUTHOR_ALREADY_EXISTS.getMessage());
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author updatedAuthor, Long id) {
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
        return authorRepository.save(existingAuthorEntity);
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> autor = authorRepository.findById(id);
        if (autor.isEmpty()) {
            throw new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND.getMessage());
        }
        return autor.get();
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> autor = authorRepository.findById(id);
        if (autor.isEmpty()) {
            throw new NotFoundException(ErrorMessage.AUTHOR_NOT_FOUND.getMessage());
        }
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }
}
