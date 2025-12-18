package com.personal.project.controller;

import com.personal.project.entities.Specie;
import com.personal.project.services.SpecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SpecieController {

    private final SpecieService specieService;

    public SpecieController(final SpecieService refSpecieService) {
        this.specieService = refSpecieService;
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
                .map(species ->
                    ResponseEntity.ok(species.stream().toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
