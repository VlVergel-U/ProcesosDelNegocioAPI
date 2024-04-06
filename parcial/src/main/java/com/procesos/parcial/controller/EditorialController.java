package com.procesos.parcial.controller;

import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.service.EditorialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/v1/editorial")
    public class EditorialController {
        private final EditorialService editorialService;

        public EditorialController(EditorialService editorialService) {
            this.editorialService = editorialService;
        }

        @GetMapping("/{id}")
        public Editorial getEditorialById(@PathVariable Long id) {
            return editorialService.getEditorialById(id);
        }

        @PutMapping("/{id}")
        public Editorial updateEditorial(@RequestBody Editorial editorial, @PathVariable Long id) {
            return editorialService.updateEditorial(editorial, id);
        }

        @GetMapping
        public List<Editorial> allEditorials() {
            return editorialService.findAllEditorials();
        }
    }



