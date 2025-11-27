package com.personal.project.controller;


import com.personal.project.model.Plant;
import com.personal.project.model.request.PlantFilterRequest;
import com.personal.project.services.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Plant controller.
 */
@RestController
@RequestMapping("/plants")
@CrossOrigin(origins = "http://localhost:4200")
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
            @RequestParam(required = false) final PlantFilterRequest filter
    ) {
        List<Plant> plants = plantService.findAllPlantsByFilter(filter);
        return plants.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(plants);
    }
}
