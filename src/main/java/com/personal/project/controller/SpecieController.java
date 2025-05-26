package com.personal.project.controller;

import com.personal.project.model.Specie;
import com.personal.project.services.SpecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Specie controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SpecieController {

    /**
     * Specie service.
     */
    private final SpecieService specieService;

    /**
     * Constructor.
     * @param refSpecieService Region service.
     */
    public SpecieController(final SpecieService refSpecieService) {
        this.specieService = refSpecieService;
    }

    /**
     * Get one specie by id.
     * @param id the id
     * @return one specie by id
     */
    @GetMapping("/species/{id}")
    public ResponseEntity<Specie> getSpecieById(
            @PathVariable("id") final Long id
    ) {
        return specieService.findSpecieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all species if data exist.
     * @return Get all species
     */
    @GetMapping("/species")
    public ResponseEntity<List<Specie>> getSpecies() {
        Optional<List<Specie>> optSpecies = specieService.findAllSpecie();
        return optSpecies
                .map(ArrayList::new)
                .map(species -> {
                    return ResponseEntity.ok(species.stream().toList());
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
