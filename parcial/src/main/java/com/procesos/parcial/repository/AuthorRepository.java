package com.procesos.parcial.repository;

import com.procesos.parcial.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByCode(String code);
}
