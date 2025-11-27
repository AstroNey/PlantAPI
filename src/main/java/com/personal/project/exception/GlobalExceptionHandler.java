package com.personal.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler.
 * This class handles exceptions thrown by the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle invalid email exception.
     * @param ex the exception
     * @return the response entity
     */
    @ExceptionHandler(InvalidMailException.class)
    public ResponseEntity<String> handleInvalidEmail(
            final InvalidMailException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
