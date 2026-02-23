package com.personal.project.controller;

import com.personal.project.dtos.filters.PlantFilter;
import com.personal.project.entities.Environment;
import com.personal.project.entities.Plant;
import com.personal.project.entities.Region;
import com.personal.project.entities.Specie;
import com.personal.project.entities.builders.PlantBuilder;
import com.personal.project.enums.LightLevel;
import com.personal.project.enums.Watering;
import com.personal.project.services.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class PlantControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantController plantController;

    private Plant plant;

    private Plant plant2;

    @BeforeEach
    void setUp() {
        Specie specie = new Specie(1L, "Specie");
        Environment environment = new Environment(1L, "Environment");
        Region region = new Region(1L, "Region");
        HashSet<Region> regions = new HashSet<>();
        regions.add(region);

        plant = new PlantBuilder()
                .setId(1L)
                .setScientificName("ScientificName")
                .setName("Name")
                .setFoliage("Foliage")
                .setFlowers("Flowers")
                .setSize(0.6)
                .setSunlight(LightLevel.MEDIUM)
                .setWatering(Watering.AQUATIC)
                .setSoil("Soil")
                .setTemperature("Temp")
                .setCare("Care")
                .setToxicity("Toxicity")
                .setImage("Image")
                .setEnvironment(environment)
                .setSpecie(specie)
                .setRegions(regions)
                .build();

        plant2 = new PlantBuilder()
                .setName("Name2")
                .setFoliage("Foliage2")
                .setFlowers("Flowers2")
                .setSize(0.7)
                .setSunlight(LightLevel.MEDIUM)
                .setWatering(Watering.AQUATIC)
                .setSoil("Soil2")
                .setTemperature("Temp2")
                .setCare("Care2")
                .setToxicity("Toxicity2")
                .setImage("Image2")
                .setEnvironment(environment)
                .setSpecie(specie)
                .setRegions(regions)
                .build();
    }

    @Test
    void getPlantByIdSuccess() {

        when(plantService.findPlantById(1L)).thenReturn(Optional.of(plant));

        ResponseEntity<Plant> response = plantController.getPlantById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plant, response.getBody());
    }

    @Test
    void getPlantByIdFail() {
        when(plantService.findPlantById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Plant> response = plantController.getPlantById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllPlants_returnsList() {
        when(plantService.findAllPlants()).thenReturn(List.of(plant, plant2));

        ResponseEntity<List<Plant>> response = plantController.getAllPlants();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getAllPlants_returnsNotFound() {
        when(plantService.findAllPlants()).thenReturn(List.of());

        ResponseEntity<List<Plant>> response = plantController.getAllPlants();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllPlantsByFilter_returnsList() {
        when(plantService.findAllPlantsByFilter(any(PlantFilter.class)))
                .thenReturn(List.of(plant));

        ResponseEntity<List<Plant>> response = plantController.getAllPlantsByFilter(
                LightLevel.MEDIUM, Watering.AQUATIC, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getAllPlantsByFilter_returnsNotFound() {
        when(plantService.findAllPlantsByFilter(any(PlantFilter.class)))
                .thenReturn(List.of());

        ResponseEntity<List<Plant>> response = plantController.getAllPlantsByFilter(
                null, null, null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}