package com.procesos.parcial.model.enums;
import lombok.Getter;

@Getter
public enum ErrorMessage {

    BOOK_NOT_FOUND("Book not found!"),
    CATEGORY_NOT_FOUND("Category not found!"),
    EDITORIAL_NOT_FOUND("Editorial not found!"),
    BOOK_ALREADY_EXISTS("Book already exists!"),
    CATEGORY_ALREADY_EXISTS("Category already exists!"),
    EDITORIAL_ALREADY_EXISTS("Editorial already exists!"),
    BOOK_HASNT_BEEN_DELETED("Book hasn't been deleted!"),
    EDITORIAL_HASNT_BEEN_DELETED("Editorial hasn't been deleted!"),
    CATEGORY_HASNT_BEEN_DELETED("Category hasn't been deleted!"),
    BOOK_HASNT_BEEN_UPDATED("Book hasn't been updated!"),
    EDITORIAL_HASNT_BEEN_UPDATED("Editorial hasn't been updated!"),
    CATEGORY_HASNT_BEEN_UPDATED("Category hasn't been updated!"),
    AUTHOR_NOT_FOUND("Author not found!"),
    AUTHOR_ALREADY_EXISTS("Author already exists!"),
    AUTHOR_HASNT_BEEN_UPDATED("Author hasn't been updated!"),
    AUTHOR_HASNT_BEEN_DELETED("Author hasn't been deleted!"),
    USER_NOT_FOUND("User not found!"),
    USER_EMAIL_EXISTS("The email is already registered"),
    INVALID_CREDENTIAL("The credentials is invalid"),
    CATEGORIES_NOT_FOUND("Categories not found!"),
    BOOKS_NOT_FOUND("Books not found!"),
    EDITORIALS_NOT_FOUND("Editorials not found!"),
    EDITORIALS_FOUND("Editorial found!"),
    AUTHORS_NOT_FOUND("Authors not found!"),
    USERS_NOT_FOUND("Users not found!");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}