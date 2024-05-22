package com.procesos.parcial.repository;

import com.procesos.parcial.model.Editorial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorialRepository extends CrudRepository<Editorial, Long> {
    Optional<Editorial> findByName(String name);
}