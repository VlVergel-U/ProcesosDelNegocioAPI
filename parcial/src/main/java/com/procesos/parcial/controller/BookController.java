package com.procesos.parcial.controller;

import com.procesos.parcial.model.Book;
import com.procesos.parcial.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired

        private BookService bookService;

        @PostMapping
        public Book createBook(@RequestBody Book book) {
            return bookService.createBook(book);
        }

        @GetMapping("/{id}")
        public Book getBookById(@PathVariable Long id) {
            return bookService.getBookById(id);
        }

        @PutMapping("/{id}")
        public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
            return bookService.updateBook(book, id);
        }

        @GetMapping
        public List<Book> allBooks() {
            return bookService.findAllBooks();
        }
    }

