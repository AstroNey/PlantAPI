package com.personal.project.controller;

import com.personal.project.model.Plant;
import com.personal.project.services.PlantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlantControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantController plantController;

    @Test
    void getPlantByIdSuccess() {
        Plant plant = new Plant(1L, "Plant 1", "Description 1", "");
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
}