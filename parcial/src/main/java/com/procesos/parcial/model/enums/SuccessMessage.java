package com.procesos.parcial.model.enums;

import lombok.Getter;

@Getter
public enum SuccessMessage {

    BOOK_CREATED("Book created successfully."),
    CATEGORY_CREATED("Category created successfully."),
    EDITORIAL_CREATED("Editorial created successfully."),
    BOOK_UPDATED("Book updated successfully."),
    CATEGORY_UPDATED("Category updated successfully."),
    EDITORIAL_UPDATED("Editorial updated successfully."),
    BOOK_DELETED("Book deleted successfully."),
    CATEGORY_DELETED("Category deleted successfully."),
    EDITORIAL_DELETED("Editorial deleted successfully."),
    AUTHOR_CREATED("Author created successfully."),
    AUTHOR_UPDATED("Author updated successfully."),
    AUTHOR_DELETED("Author deleted successfully."),
    USER_CREATED("User created successfully."),
    USER_UPDATED("User updated successfully."),
    USER_DELETED("User deleted successfully."),
    LOGIN_SUCCESSFUL("Login successful."),
    LOGOUT_SUCCESSFUL("Logout successful."),
    OPERATION_SUCCESSFUL("Operation successful."),
    AUTHORS_FOUND("Authors found!"),
    BOOKS_FOUND("Books found!"),
    BOOK_FOUND("Book found."),
    CATEGORY_FOUND("Category found."),
    EDITORIAL_FOUND("Editorial found."),
    AUTHOR_FOUND("Author found."),
    USER_FOUND("User found."),
    CATEGORIES_FOUND("Categories found!"),
    EDITORIALS_FOUND("Editorials found!"),
    USERS_FOUND("Users found!");

    private final String message;

    SuccessMessage (String message) {
        this.message = message;
    }

}