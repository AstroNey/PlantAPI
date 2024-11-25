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
class PlantTest {

    private Validator validator;

    private Plant plant;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        plant = new Plant(1L, "Plant", "Description", "Image");
    }

    @Test
    void testValidPlant() {
        assertEquals(1L, plant.getId());
        assertEquals("Plant", plant.getName());
        assertEquals("Description", plant.getDescription());
        assertEquals("Image", plant.getImage());
        validator.validate(plant).forEach(System.out::println);
        assertTrue(validator.validate(plant).isEmpty(), "Expected no constraint violation");
    }

    @Test
    void testInvalidPlant() {
        plant.setName(null);
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant.setName("");
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant.setDescription(null);
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant.setDescription("");
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testSetImage() {
        plant.setImage("NewImage");
        assertEquals("NewImage", plant.getImage());

        validator.validate(plant).forEach(System.out::println);
        assertTrue(validator.validate(plant).isEmpty(), "Expected no constraint violation");
    }
}