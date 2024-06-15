package com.procesos.parcial.service;

import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.dto.ResponseUtil;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.model.enums.SuccessMessage;
import com.procesos.parcial.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceOp implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private ResponseUtil response;

    @Override
    public Response createEditorial(Editorial editorial) {
        Optional<Editorial> findByName = editorialRepository.findByName(editorial.getName());
        if(findByName.isPresent()){
            throw  new AlreadyExistsException(ErrorMessage.EDITORIAL_ALREADY_EXISTS.getMessage());
        }
        editorialRepository.save(editorial);
        return response.generateResponse(SuccessMessage.EDITORIAL_CREATED.getMessage(), editorial, String.valueOf(HttpStatus.CREATED));
    }


    @Override
    public Response updateEditorial(Editorial editorialUpdated, Long id) {
        Optional<Editorial> editorial = editorialRepository.findById(id);
        if (editorial.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.EDITORIAL_NOT_FOUND.getMessage());
        }
        Editorial existingEditorial = editorial.get();
        existingEditorial.setName(editorialUpdated.getName());
        existingEditorial.setCity(editorialUpdated.getCity());
        existingEditorial.setCountry(editorialUpdated.getCountry());
        existingEditorial.setPhoneNumber(editorialUpdated.getPhoneNumber());
        existingEditorial.setAddress(editorialUpdated.getAddress());
        editorialRepository.save(existingEditorial);
        return response.generateResponse(SuccessMessage.EDITORIAL_UPDATED.getMessage(), existingEditorial, String.valueOf(HttpStatus.OK));

    }


    @Override
    public Response getEditorialById(Long id) {
        Optional<Editorial> editorial = editorialRepository.findById(id);
        if (editorial.isEmpty()) {
            throw  new AlreadyExistsException(ErrorMessage.EDITORIAL_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.EDITORIAL_FOUND.getMessage(), editorial, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response deleteEditorial(Long id) {
        Optional<Editorial> editorial = editorialRepository.findById(id);
        if (editorial.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.EDITORIAL_NOT_FOUND.getMessage());
        } else {
            try {
                editorialRepository.deleteById(id);
                return response.generateResponse(SuccessMessage.EDITORIAL_DELETED.getMessage(), editorial, String.valueOf(HttpStatus.OK));

            } catch (Exception e) {
                throw new AlreadyExistsException(ErrorMessage.EDITORIAL_HASNT_BEEN_DELETED.getMessage());
            }
        }
    }

    @Override
    public Response findAllEditorials() {
        List<Editorial> editorials = (List<Editorial>) editorialRepository.findAll();

        if (editorials.isEmpty()) {
            throw new AlreadyExistsException(ErrorMessage.EDITORIALS_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.EDITORIALS_FOUND.getMessage(), editorials, String.valueOf(HttpStatus.OK));

    }
}