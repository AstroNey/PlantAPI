package com.personal.project.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidMailExceptionTest {

    @Test
    void constructorSetsMessage() {
        InvalidMailException exception = new InvalidMailException("Invalid email format");
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void isRuntimeException() {
        InvalidMailException exception = new InvalidMailException("test");
        assertInstanceOf(RuntimeException.class, exception);
    }
}

