package com.personal.project.services;

import com.personal.project.dtos.filters.PlantFilter;
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
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void findAllPlants_returnsList() {
        Plant plant1 = new PlantBuilder().setId(1L).setName("Plant1").build();
        Plant plant2 = new PlantBuilder().setId(2L).setName("Plant2").build();
        when(plantRepository.findAll()).thenReturn(List.of(plant1, plant2));

        List<Plant> plants = plantService.findAllPlants();
        assertEquals(2, plants.size());
    }

    @Test
    void findAllPlants_returnsEmptyList() {
        when(plantRepository.findAll()).thenReturn(List.of());

        List<Plant> plants = plantService.findAllPlants();
        assertTrue(plants.isEmpty());
    }

    @Test
    void findAllPlantsByFilter_withAllFiltersNull_returnsAll() {
        PlantFilter filter = new PlantFilter(null, null, null, null);
        Plant plant1 = new PlantBuilder().setId(1L).setName("Plant1").build();
        when(plantRepository.findAll()).thenReturn(List.of(plant1));

        List<Plant> plants = plantService.findAllPlantsByFilter(filter);
        assertEquals(1, plants.size());
    }

    @Test
    void findAllPlantsByFilter_withNameFilter() {
        PlantFilter filter = new PlantFilter("Rose", null, null, null);
        Plant plant1 = new PlantBuilder().setId(1L).setName("Rose").build();
        when(plantRepository.findAll(any(Specification.class))).thenReturn(List.of(plant1));

        List<Plant> plants = plantService.findAllPlantsByFilter(filter);
        assertEquals(1, plants.size());
    }

    @Test
    void findAllPlantsByFilter_withMultipleFilters() {
        PlantFilter filter = new PlantFilter("Rose", LightLevel.HIGH, Watering.MEDIUM, true);
        Plant plant1 = new PlantBuilder().setId(1L).setName("Rose")
                .setSunlight(LightLevel.HIGH).setWatering(Watering.MEDIUM).build();
        when(plantRepository.findAll(any(Specification.class))).thenReturn(List.of(plant1));

        List<Plant> plants = plantService.findAllPlantsByFilter(filter);
        assertEquals(1, plants.size());
    }

    @Test
    void findAllPlantsByFilter_withSingleFilter() {
        PlantFilter filter = new PlantFilter(null, null, null, true);
        when(plantRepository.findAll(any(Specification.class))).thenReturn(List.of());

        List<Plant> plants = plantService.findAllPlantsByFilter(filter);
        assertTrue(plants.isEmpty());
    }
}