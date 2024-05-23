package com.procesos.parcial.Validations;

import com.procesos.parcial.model.Author;

public class AuthorValidator {

    public boolean isValid(Author author) {
        // Validar los campos de autor aquí
        if (author.getFirstName() == null || author.getFirstName().trim().isEmpty()) {
            return false;
        }
        if (author.getFirstLastName() == null || author.getFirstLastName().trim().isEmpty()) {
            return false;
        }
        if (author.getSecondLastName() == null || author.getSecondLastName().trim().isEmpty()) {
            return false;
        }
        if (author.getBirthDate() == null) {
            return false;
        }
        return true;
    }
}
