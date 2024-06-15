package com.procesos.parcial.controller;

import com.procesos.parcial.model.Author;
import com.procesos.parcial.model.dto.Request;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Response> createAuthor(@Valid @RequestBody Request<Author> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(request.getObject()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAuthorById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(authorService.getAuthorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateAuthor(@Valid @RequestBody Request<Author> request, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(authorService.updateAuthor(request.getObject(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAuthor(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(authorService.deleteAuthor(id));

    }

    @GetMapping
    public ResponseEntity<Response> allAuthors() {
        return ResponseEntity.ok().body(authorService.findAllAuthors());
    }
}