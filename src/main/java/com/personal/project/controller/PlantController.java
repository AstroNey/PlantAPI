package com.personal.project.controller;


import com.personal.project.model.Plant;
import com.personal.project.services.PlantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
     * @param size the number of elements we want in the page.
     * @return Get all plants with limit.
     */
    @GetMapping("/plants")
    ResponseEntity<Page<Plant>> getPlantsWithLimits(
            @RequestParam final int size) {
        Pageable pageable = PageRequest.of(0, size);
        Page<Plant> plants = plantService.findPlantsWithLimit(pageable);
        return plants.hasContent()
                ? ResponseEntity.ok(plants) : ResponseEntity.notFound().build();
    }
}
