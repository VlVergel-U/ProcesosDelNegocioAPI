package com.procesos.parcial.controller;

import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.dto.Request;
import com.procesos.parcial.service.EditorialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
@Validated
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @PostMapping
    public ResponseEntity<Editorial> createEditorial(@Valid @RequestBody Request<Editorial> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.createEditorial(request.getObject()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorial> getEditorialById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(editorialService.getEditorialById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> updateEditorial(@Valid @RequestBody Request<Editorial> request, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(editorialService.updateEditorial(request.getObject(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditorial(@PathVariable @Min(1) Long id) {
        editorialService.deleteEditorial(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Editorial>> allEditorials() {
        return ResponseEntity.ok().body(editorialService.findAllEditorials());
    }
}