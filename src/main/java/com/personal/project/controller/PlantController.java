package com.personal.project.controller;


import com.personal.project.model.Plant;
import com.personal.project.services.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Plant controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlantController {

    /**
     * Plant service.
     */
    private final PlantService plantService;

    /**
     * Constructor for PlantController.
     * @param refPlantService the plant service
     */
    public PlantController(final PlantService refPlantService) {
        this.plantService = refPlantService;
    }

    /**
     * Get one plant by id.
     * @param id the id
     * @return one plant by id
     */
    @GetMapping("/plants/{id}")
    public ResponseEntity<Plant> getPlantById(
            @PathVariable("id") final Long id
    ) {
        return plantService.findPlantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all plants if data exist.
     * @return Get all plants
     */
    @GetMapping("/plants")
    public ResponseEntity<List<Plant>> getPlants() {
        Iterable<Plant> iterablePlants = plantService.findPlants();
        List<Plant> plants = new ArrayList<>();
        iterablePlants.forEach(plants::add);
        return !plants.isEmpty() ?  ResponseEntity.ok(plants) : ResponseEntity.notFound().build();
    }
}
