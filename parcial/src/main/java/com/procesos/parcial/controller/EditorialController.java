package com.procesos.parcial.controller;

import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @PostMapping
    public ResponseEntity<Editorial> createEditorial(@RequestBody Editorial editorial) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.createEditorial(editorial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> getEditorialById(@PathVariable Long id) {
        return ResponseEntity.ok().body(editorialService.getEditorialById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> updateEditorial(@RequestBody Editorial editorial, @PathVariable Long id) {
        return ResponseEntity.ok().body(editorialService.updateEditorial(editorial, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditorial(@PathVariable Long id) {
        editorialService.deleteEditorial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Editorial>> allEditorials() {
        return ResponseEntity.ok().body(editorialService.findAllEditorials());
    }
}