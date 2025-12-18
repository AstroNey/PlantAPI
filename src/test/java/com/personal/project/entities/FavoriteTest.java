package com.personal.project.entities;

import com.personal.project.entities.builders.PlantBuilder;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FavoriteTest {

    private Validator validator;

    private Favorite favorite;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        User user = new User(1L, "username",  "email", "password");
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

        // Fix: Use the AllArgsConstructor to create FavoriteId with both IDs
        FavoriteId favoriteId = new FavoriteId(1L, 1L); // idUser, idPlant

        favorite = new Favorite(favoriteId, plant, user, LocalDate.now());
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
