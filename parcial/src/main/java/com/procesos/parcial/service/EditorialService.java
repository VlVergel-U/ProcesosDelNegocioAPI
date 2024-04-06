package com.procesos.parcial.service;

import com.procesos.parcial.model.Editorial;

import java.util.List;

public interface EditorialService {

    Editorial updateEditorial(Editorial editorial, Long id);
    Editorial getEditorialById(Long id);
    List<Editorial> findAllEditorials();

}
