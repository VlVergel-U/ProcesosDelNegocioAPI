package com.procesos.parcial.controller;

import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.dto.Request;
import com.procesos.parcial.model.dto.Response;
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
    public ResponseEntity<Response> createEditorial(@Valid @RequestBody Request<Editorial> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.createEditorial(request.getObject()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEditorialById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(editorialService.getEditorialById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateEditorial(@Valid @RequestBody Request<Editorial> request, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(editorialService.updateEditorial(request.getObject(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteEditorial(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(editorialService.deleteEditorial(id));
    }

    @GetMapping
    public ResponseEntity<Response> allEditorials() {
        return ResponseEntity.ok().body(editorialService.findAllEditorials());
    }
}