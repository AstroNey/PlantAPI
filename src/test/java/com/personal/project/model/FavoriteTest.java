package com.personal.project.model;

import com.personal.project.model.builders.PlantBuilder;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavoriteTest {

    private Validator validator;

    private Favorite favorite;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        User user = new User(1L, "username", "password", "email", "ROLE_USER");
        Plant plant = new PlantBuilder()
                .setId(1L)
                .setScientificName("Ficus lyrata")
                .setName("Fiddle Leaf Fig")
                .setFoliage("Large, violin-shaped leaves")
                .setFlowers("Small, insignificant flowers")
                .setSize(150.0)
                .setSunlight("Bright, indirect light")
                .setWatering("Water when top inch of soil is dry")
                .setSoil("Well-draining potting mix")
                .setTemperature("65-75°F (18-24°C)")
                .setCare("Wipe leaves to remove dust, rotate regularly")
                .setToxicity("Toxic to pets if ingested")
                .setImage("ficus_lyrata.jpg")
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
