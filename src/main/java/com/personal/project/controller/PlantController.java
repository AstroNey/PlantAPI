package com.personal.project.controller;


import com.personal.project.model.Plant;
import com.personal.project.services.PlantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Plant controller.
 */
@RestController
@RequestMapping("/plants")
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
     * @param name the name
     * @param idRegion the id region
     * @return all plants by filter
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Plant>> getAllPlantsByFilter(
            @RequestParam(value = "name", required = false, defaultValue = "") final String name,
            @RequestParam(value = "idRegion", required = false, defaultValue = "0") final long idRegion
    ) {
        List<Plant> plants = plantService.findAllPlantsByFilter(name, idRegion);
        return plants.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(plants);
    }
}
