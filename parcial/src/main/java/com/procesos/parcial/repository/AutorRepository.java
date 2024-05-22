package com.procesos.parcial.repository;

import com.procesos.parcial.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AutorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByUniqueCode(String uniqueCode);
}
