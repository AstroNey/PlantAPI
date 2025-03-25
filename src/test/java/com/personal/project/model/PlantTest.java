package com.personal.project.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PlantTest {

    private Validator validator;

    private Plant plant;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Specie specie = new Specie(1L, "Specie");
        Environment environment = new Environment(1L, "Environment");

        Region region = new Region(1L, "Region");
        HashSet<Region> regions = new HashSet<>();
        regions.add(region);

        User user = new User(1L, "username", "password", "email", "ROLE_USER");
        FavoriteId favoriteId = new FavoriteId(user.getId(), 1L);
        Favorite favorite = new Favorite(favoriteId, user, plant);
        HashSet<Favorite> favorites = new HashSet<>();
        favorites.add(favorite);

        plant = new Plant.Builder()
                .setId(1L)
                .setScientificName("ScientificName")
                .setName("Name")
                .setDescription("Description")
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
                .setFavorites(favorites)
                .build();
    }

    @Test
    void testValidPlantEssential() {
        assertEquals(1L, plant.getId());
        assertEquals("ScientificName", plant.getScientificName());
        assertEquals("Name", plant.getName());
        assertEquals("Description", plant.getDescription());
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
        plant = new Plant.Builder().setId(-1L).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void  testInvalidPlantScientificName() {
        plant = new Plant.Builder().setScientificName(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setScientificName("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantName() {
        plant = new Plant.Builder().setName(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setName("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantDescription() {
        plant = new Plant.Builder().setDescription(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setDescription("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantFoliage() {
        plant = new Plant.Builder().setFoliage(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setFoliage("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantSize() {
        plant = new Plant.Builder().setSize(-0.1).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setSize(0.0).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantFlowers() {
        plant = new Plant.Builder().setFlowers(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setFlowers("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantImage() {
        plant = new Plant.Builder().setImage(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setImage("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantWatering() {
        plant = new Plant.Builder().setWatering(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setWatering("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantSoil() {
        plant = new Plant.Builder().setSoil(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setSoil("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantSunlight() {
        plant = new Plant.Builder().setSunlight(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setSunlight("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantTemperature() {
        plant = new Plant.Builder().setTemperature(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setTemperature("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setTemperature("12345678901").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation for temperature too long");
    }

    @Test
    void testInvalidPlantCare() {
        plant = new Plant.Builder().setCare(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setCare("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantToxicity() {
        plant = new Plant.Builder().setToxicity(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setToxicity("").build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }

    @Test
    void testInvalidPlantRelation() {
        plant = new Plant.Builder().setEnvironment(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant = new Plant.Builder().setSpecie(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant = new Plant.Builder().setRegions(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setRegions(new HashSet<>()).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");

        plant = new Plant.Builder().setFavorites(null).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
        plant = new Plant.Builder().setFavorites(new HashSet<>()).build();
        assertFalse(validator.validate(plant).isEmpty(), "Expected constraint violation");
    }
}