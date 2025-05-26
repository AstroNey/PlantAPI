package com.personal.project.controller;


import com.personal.project.model.Environment;
import com.personal.project.services.EnvironmentService;
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
public class EnvironmentController {

    /**
     * Environment service.
     */
    private final EnvironmentService environmentService;

    /**
     * Constructor for EnvironmentController.
     * @param refEnvironmentService the environment service
     */
    public EnvironmentController(
            final EnvironmentService refEnvironmentService) {
        this.environmentService = refEnvironmentService;
    }

    /**
     * Get one environment by id.
     * @param id the id
     * @return one environment by id
     */
    @GetMapping("/environments/{id}")
    public ResponseEntity<Environment> getEnvironmentById(
            @PathVariable("id") final Long id
    ) {
        return environmentService.findEnvironmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all environments if data exist.
     * @return Get all environments
     */
    @GetMapping("/environments")
    public ResponseEntity<List<Environment>> getEnvironments() {
        Optional<List<Environment>> optEnvironments
                = environmentService.findAllEnvironment();
        return optEnvironments
                .map(ArrayList::new)
                .map(environments ->
                        ResponseEntity.ok(environments.stream().toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
