package com.procesos.parcial.service;

import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.repository.EditorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceOp implements EditorialService {

    private final EditorialRepository editorialRepository;

    public EditorialServiceOp(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @Override
    public Editorial createEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Editorial updateEditorial(Editorial editorialUpdated, Long id) {
        Optional<Editorial> editorialBD = editorialRepository.findById(id);
        if (editorialBD.isEmpty()) {
            return null;
        }
        Editorial existingEditorial = editorialBD.get();
        existingEditorial.setName(editorialUpdated.getName());
        existingEditorial.setCity(editorialUpdated.getCity());
        existingEditorial.setCountry(editorialUpdated.getCountry());
        existingEditorial.setPhoneNumber(editorialUpdated.getPhoneNumber());
        existingEditorial.setAddress(editorialUpdated.getAddress());
        return editorialRepository.save(existingEditorial);
    }

    @Override
    public Editorial getEditorialById(Long id) {
        Optional<Editorial> editorialBD = editorialRepository.findById(id);
        return editorialBD.orElse(null);
    }

    @Override
    public void deleteEditorial(Long id) {
        editorialRepository.deleteById(id);
    }

    @Override
    public List<Editorial> findAllEditorials() {
        return (List<Editorial>) editorialRepository.findAll();
    }
}