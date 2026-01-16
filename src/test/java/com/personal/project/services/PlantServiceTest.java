package com.personal.project.services;

import com.personal.project.entities.Plant;
import com.personal.project.entities.builders.PlantBuilder;
import com.personal.project.enums.LightLevel;
import com.personal.project.enums.Watering;
import com.personal.project.repository.PlantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private PlantService plantService;

    @Test
    void findPlantByIdSucces() {
        Plant expectedPlant = new PlantBuilder()
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
                .build();
        when(plantRepository.findById(1L)).thenReturn(Optional.of(expectedPlant));

        Optional<Plant> actualPlant = plantService.findPlantById(1L);
        assertTrue(actualPlant.isPresent());
        assertEquals(1L, actualPlant.get().getId());
    }

    @Test
    void findPlantByIdFail() {
        when(plantRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Plant> foundPlant = plantService.findPlantById(1L);
        assertTrue(foundPlant.isEmpty());
    }

    //TODO GET ALL PLANTS TESTS
}