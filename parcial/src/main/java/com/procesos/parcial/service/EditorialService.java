package com.procesos.parcial.service;

import com.procesos.parcial.model.Category;
import com.procesos.parcial.model.Editorial;

import java.util.List;

public interface EditorialService {
    Editorial createEditorial(Editorial editorial);
    Editorial updateEditorial(Editorial editorial, Long id);
    Editorial getEditorialById(Long id);
    void deleteEditorial(Long id);
    List<Editorial> findAllEditorials();

}
