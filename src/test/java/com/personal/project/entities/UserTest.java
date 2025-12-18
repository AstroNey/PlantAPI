package com.personal.project.entities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private Validator validator;

    private User user;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        user = new User(1L, "username", "email", "password");
    }

    @Test
    void testValidUser() {
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());

        validator.validate(user).forEach(System.out::println);
        assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    void testInvalidUser() {
        user = new User(1L, null, "email", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "", "email", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");

        user = new User(1L, "username", null, "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "username", "", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");

        user = new User(1L, "username", "email", null);
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "username", "email", "");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
    }
}
