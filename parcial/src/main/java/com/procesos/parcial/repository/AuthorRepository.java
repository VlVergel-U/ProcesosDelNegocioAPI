package com.procesos.parcial.repository;

import com.procesos.parcial.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByCode(String code);

    Author findByFirstNameAndSecondNameAndFirstLastNameAndSecondLastName(String firstName, String secondName, String firstLastName, String secondLastName);
}
