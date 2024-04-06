package com.procesos.parcial.repository;

import com.procesos.parcial.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
