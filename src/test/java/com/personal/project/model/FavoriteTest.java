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
class FavoriteTest {

    private Validator validator;

    private Favorite favorite;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        User user = new User(1L, "username", "password", "email", "ROLE_USER");
        Plant plant = new Plant.Builder()
                .setId(1L)
                .setScientificName("ScientificName")
                .setName("Name")
                .setFoliage("Foliage")
                .setFlowers("Flowers")
                .setSize(0.6)
                .setSunlight("Sunlight")
                .setWatering("Watering")
                .setSoil("Soil")
                .setTemperature("Temp")
                .setCare("Care")
                .setToxicity("Toxicity")
                .setImage("Image")
                .build();
        favorite = new Favorite(user, plant);
        plant.addFavorite(favorite);
    }

    @Test
    void testValidFavorite() {
        assertEquals(1L, favorite.getId().getIdUser());
        assertEquals(1L, favorite.getId().getIdPlant());

        validator.validate(favorite).forEach(System.out::println);
        assertTrue(validator.validate(favorite).isEmpty());
    }
}
