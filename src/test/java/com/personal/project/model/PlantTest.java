package com.personal.project.model;

import com.personal.project.model.builders.PlantBuilder;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PlantTest {

    private Validator validator;

    private Plant plant;

    @BeforeEach
    void setUp() {
        // Initialisation du validator
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        // Création des entités simples
        Specie specie = new Specie(1L, "Specie");
        Environment environment = new Environment(1L, "Environment");

        Region region = new Region(1L, "Region");
        Set<Region> regions = new HashSet<>();
        regions.add(region);

        User user = new User(1L, "username", "password", "email", "ROLE_USER");

        // Création de la plante (sans les favoris pour l'instant)
        plant = new PlantBuilder()
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
                .setEnvironment(environment)
                .setSpecie(specie)
                .setRegions(regions)
                .build();

        // Création des favoris maintenant que la plante est disponible
        FavoriteId favoriteId = new FavoriteId(user.getId(), plant.getId());
        Favorite favorite = new Favorite(favoriteId, plant, user, LocalDate.ofInstant(new Date().toInstant(), java.time.ZoneId.systemDefault()));
        // Ajout des favoris à la plante
        plant.addFavorite(favorite);
    }

    @Test
    void testValidPlantEssential() {
        assertEquals(1L, plant.getId());
        assertEquals("ScientificName", plant.getScientificName());
        assertEquals("Name", plant.getName());
        assertEquals("Foliage", plant.getFoliage());
        assertEquals("Flowers", plant.getFlowers());
        assertEquals(0.6, plant.getSize());
        assertEquals("Sunlight", plant.getSunlight());
        assertEquals("Watering", plant.getWatering());
        assertEquals("Soil", plant.getSoil());
        assertEquals("Temp", plant.getTemperature());
        assertEquals("Care", plant.getCare());
        assertEquals("Toxicity", plant.getToxicity());
        assertEquals("Image", plant.getImage());

        validator.validate(plant).forEach(System.out::println);
        assertTrue(validator.validate(plant).isEmpty(), "Expected no constraint violation");
    }

    @Test
    void testValidPlantRelation() {
        assertEquals(1L, plant.getEnvironment().getId());
        assertEquals("Environment", plant.getEnvironment().getName());
        assertEquals(1L, plant.getSpecie().getId());
        assertEquals("Specie", plant.getSpecie().getName());
        assertEquals(1, plant.getRegions().size());
        assertEquals(1L, plant.getRegions().iterator().next().getId());
        assertEquals("Region", plant.getRegions().iterator().next().getName());
        assertEquals(1, plant.getFavorites().size());
        assertEquals(1L, plant.getFavorites().iterator().next().getId().getIdUser());
        assertEquals(1L, plant.getFavorites().iterator().next().getId().getIdPlant());

        validator.validate(plant).forEach(System.out::println);
        assertTrue(validator.validate(plant).isEmpty(), "Expected no constraint violation");
    }

    @Test
    void testInvalidPlantId() {
        plant = new PlantBuilder().setId(-1L).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void  testInvalidPlantScientificName() {
        plant = new PlantBuilder().setScientificName(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setScientificName("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantName() {
        plant = new PlantBuilder().setName(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setName("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantFoliage() {
        plant = new PlantBuilder().setFoliage(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setFoliage("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantSize() {
        plant = new PlantBuilder().setSize(-0.1).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setSize(0.0).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantFlowers() {
        plant = new PlantBuilder().setFlowers(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setFlowers("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantImage() {
        plant = new PlantBuilder().setImage(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setImage("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantWatering() {
        plant = new PlantBuilder().setWatering(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setWatering("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantSoil() {
        plant = new PlantBuilder().setSoil(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setSoil("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantSunlight() {
        plant = new PlantBuilder().setSunlight(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setSunlight("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantTemperature() {
        plant = new PlantBuilder().setTemperature(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setTemperature("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setTemperature("12345678901").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation for temperature too long");
    }

    @Test
    void testInvalidPlantCare() {
        plant = new PlantBuilder().setCare(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setCare("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantToxicity() {
        plant = new PlantBuilder().setToxicity(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setToxicity("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantRelation() {
        plant = new PlantBuilder().setEnvironment(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant = new PlantBuilder().setSpecie(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant = new PlantBuilder().setRegions(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setRegions(new HashSet<>()).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant = new PlantBuilder().setFavorites(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new PlantBuilder().setFavorites(new HashSet<>()).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }
}