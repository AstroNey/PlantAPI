package com.personal.project.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class EnvironmentTest {

    private Validator validator;

    private Environment environment;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        environment = new Environment(1L, "Environment");
    }

    @Test
    void testValidEnvironment() {
        assertEquals(1L, environment.getId());
        assertEquals("Environment", environment.getName());

        validator.validate(environment).forEach(System.out::println);
        assertTrue(validator.validate(environment).isEmpty());
    }

    @Test
    void testInvalidEnvironment() {
        environment = new Environment(1L, null);
        assertFalse(validator.validate(environment).isEmpty(), "Expected constraint violation");
        environment = new Environment(1L, "");
        assertFalse(validator.validate(environment).isEmpty(), "Expected constraint violation");
    }
}
