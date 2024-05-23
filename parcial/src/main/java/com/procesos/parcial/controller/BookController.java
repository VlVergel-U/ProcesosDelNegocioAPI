package com.procesos.parcial.controller;

import com.procesos.parcial.model.Book;
import com.procesos.parcial.model.dto.Request;
import com.procesos.parcial.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Request<Book> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request.getObject()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable @Min(1) Long id) {

        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Request<Book> request, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(bookService.updateBook(request.getObject(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable @Min(1) Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> allBooks() {
        return ResponseEntity.ok().body(bookService.findAllBooks());
    }
}
