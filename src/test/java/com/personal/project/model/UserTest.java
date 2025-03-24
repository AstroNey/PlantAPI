package com.personal.project.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

    private Validator validator;

    private User user;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        user = new User(1L, "username", "lastName", "email", "password");
    }

    @Test
    void testValidUser() {
        assertEquals(1L, user.getId());
        assertEquals("username", user.getName());
        assertEquals("lastName", user.getLastName());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());

        validator.validate(user).forEach(System.out::println);
        assertTrue(validator.validate(user).isEmpty());
    }

    @Test
    void testInvalidUser() {
        user = new User(1L, null, "lastName", "email", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "", "lastName", "email", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");

        user = new User(1L, "username", null, "email", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "username", "", "email", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");

        user = new User(1L, "username", "lastName", null, "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "username", "lastName", "", "password");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");

        user = new User(1L, "username", "lastName", "email", null);
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
        user = new User(1L, "username", "lastName", "email", "");
        assertFalse(validator.validate(user).isEmpty(), "Expected constraint violation");
    }
}
