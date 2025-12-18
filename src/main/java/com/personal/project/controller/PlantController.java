package com.personal.project.controller;


import com.personal.project.dtos.filters.PlantFilter;
import com.personal.project.entities.Plant;
import com.personal.project.services.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Plant controller.
 */
@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(final PlantService refPlantService) {
        this.plantService = refPlantService;
    }

    /**
     * Get one plant by id.
     * @param id the id
     * @return one plant by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(
            @PathVariable("id") final Long id
    ) {
        return plantService.findPlantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all plants.
     * @return all plants
     */
    @GetMapping()
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.findAllPlants();
        return plants.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(plants);
    }

    /**
     * Get all plants by filter.
     * @param filter the filter
     * @return all plants by filter
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Plant>> getAllPlantsByFilter(
            @RequestParam(required = false) final PlantFilter filter
    ) {
        List<Plant> plants = plantService.findAllPlantsByFilter(filter);
        return plants.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(plants);
    }
}
