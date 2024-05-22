package com.procesos.parcial.repository;

import com.procesos.parcial.model.Autor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AutorRepository extends CrudRepository<Autor, Long> {
    Optional<Autor> findByUniqueCode(String uniqueCode);
}
