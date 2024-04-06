package com.procesos.parcial.repository;

import com.procesos.parcial.model.Book;
import com.procesos.parcial.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
