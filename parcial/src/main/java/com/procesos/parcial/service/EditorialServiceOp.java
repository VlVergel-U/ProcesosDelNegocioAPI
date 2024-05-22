package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceOp implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public Editorial createEditorial(Editorial editorial) {
        Optional<Editorial> findByName = editorialRepository.findByName(editorial.getName());
        if(findByName.isPresent()){
            throw  new AlreadyExistsException(ErrorMessage.EDITORIAL_ALREADY_EXISTS.getMessage());
        }
        return editorialRepository.save(editorial);
    }


    @Override
    public Editorial updateEditorial(Editorial editorialUpdated, Long id) {
        Optional<Editorial> editorialBD = editorialRepository.findById(id);
        if (editorialBD.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.EDITORIAL_NOT_FOUND.getMessage());
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
        if (editorialBD.isEmpty()) {
            throw  new AlreadyExistsException(ErrorMessage.EDITORIAL_NOT_FOUND.getMessage());
        }
        return editorialBD.get();
    }

    @Override
    public void deleteEditorial(Long id) {
        Optional<Editorial> editorialBD = editorialRepository.findById(id);
        if (editorialBD.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.EDITORIAL_NOT_FOUND.getMessage());
        } else {
            try {
                editorialRepository.deleteById(id);
            } catch (Exception e) {
                throw new AlreadyExistsException(ErrorMessage.EDITORIAL_HASNT_BEEN_DELETED.getMessage());
            }
        }
    }

    @Override
    public List<Editorial> findAllEditorials() {
        return (List<Editorial>) editorialRepository.findAll();
    }
}