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
public class RegionTest {

    private Validator validator;

    private Region region;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        region = new Region(1L, "Region");
    }

    @Test
    void testValidRegion() {
        assertEquals(1L, region.getId());
        assertEquals("Region", region.getName());

        validator.validate(region).forEach(System.out::println);
        assertTrue(validator.validate(region).isEmpty());
    }

    @Test
    void testInvalidRegion() {
        region = new Region(1L, null);
        assertFalse(validator.validate(region).isEmpty(), "Expected constraint violation");
        region = new Region(1L, "");
        assertFalse(validator.validate(region).isEmpty(), "Expected constraint violation");
    }
}
