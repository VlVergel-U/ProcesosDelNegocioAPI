package com.procesos.parcial.model.enums;
import lombok.Getter;

@Getter
public enum ErrorMessage {

    BOOK_NOT_FOUND("Book not found!"),
    CATEGORY_NOT_FOUND("Category not found!"),
    EDITORIAL_NOT_FOUND("Editor not found!"),
    BOOK_ALREADY_EXISTS("Book already exists!"),
    CATEGORY_ALREADY_EXISTS("Category already exists!"),
    EDITORIAL_ALREADY_EXISTS("Editor already exists!"),
    BOOK_HASNT_BEEN_DELETED("Book hasn't been deleted!"),
    EDITORIAL_HASNT_BEEN_DELETED("Editor hasn't been deleted!"),
    CATEGORY_HASNT_BEEN_DELETED("Category hasn't been deleted!"),
    BOOK_HASNT_BEEN_UPDATED("Book hasn't been updated!"),
    EDITORIAL_HASNT_BEEN_UPDATED("Editorial hasn't been updated!"),
    CATEGORY_HASNT_BEEN_UPDATED("Category hasn't been updated!"),
    AUTHOR_NOT_FOUND("Author not found!"),
    AUTHOR_ALREADY_EXISTS("Author already exists!"),
    AUTHOR_HASNT_BEEN_UPDATED("Author hasn't been updated!"),
    AUTHOR_HASNT_BEEN_DELETED("Author hasn't been deleted!");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
