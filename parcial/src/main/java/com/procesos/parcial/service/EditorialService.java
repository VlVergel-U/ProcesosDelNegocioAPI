package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.dto.Response;

import java.util.List;

public interface EditorialService {
    Response createEditorial(Editorial editorial);
    Response updateEditorial(Editorial editorial, Long id);
    Response getEditorialById(Long id);
    Response deleteEditorial(Long id);
    Response findAllEditorials();

}