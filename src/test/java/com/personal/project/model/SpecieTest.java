package com.personal.project.model;

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
public class SpecieTest {

    private Validator validator;

    private Specie specie;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        specie = new Specie(1L, "Specie");
    }

    @Test
    void testValidSpecie() {
        assertEquals(1L, specie.getId());
        assertEquals("Specie", specie.getName());

        validator.validate(specie).forEach(System.out::println);
        assertTrue(validator.validate(specie).isEmpty());
    }

    @Test
    void testInvalidSpecie() {
        specie = new Specie(1L, null);
        assertFalse(validator.validate(specie).isEmpty(), "Expected constraint violation");
        specie = new Specie(1L, "");
        assertFalse(validator.validate(specie).isEmpty(), "Expected constraint violation");
    }
}
