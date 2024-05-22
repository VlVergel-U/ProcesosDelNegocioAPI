package com.procesos.parcial.exceptions;


import com.procesos.parcial.model.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
@ControllerAdvice

public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> notFoundExceptionHandler(NotFoundException notFoundException){
        return new ResponseEntity<>(
                Response.builder()
                        .date(LocalDate.now())
                        .message(List.of(notFoundException.getMessage()))
                        .statusCode(HttpStatus.NOT_FOUND.name())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Response> AlreadyExistsExceptionHandler(AlreadyExistsException AlreadyExistsException){
        return new ResponseEntity<>(
                Response.builder()
                        .date(LocalDate.now())
                        .message(List.of(AlreadyExistsException.getMessage()))
                        .statusCode(HttpStatus.CONFLICT.name())
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException ex) {
        logger.error("Validation error occurred: {}", ex.getMessage(), ex);
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(
                Response.builder()
                        .date(LocalDate.now())
                        .message(errorMessages)
                        .statusCode(HttpStatus.BAD_REQUEST.name())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DuplicateAutorException.class)
    public ResponseEntity<Response> handleDuplicateAutorException(DuplicateAutorException ex) {
        return new ResponseEntity<>(
                Response.builder()
                        .date(LocalDate.now())
                        .message(List.of(ex.getMessage()))
                        .statusCode(HttpStatus.CONFLICT.name())
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Response> handleNullPointerException(NullPointerException ex) {
        logger.error("NullPointerException occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(
                Response.builder()
                        .date(LocalDate.now())
                        .message(List.of("An unexpected error occurred"))
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
