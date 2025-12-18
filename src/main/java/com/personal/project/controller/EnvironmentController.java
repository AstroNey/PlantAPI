package com.personal.project.controller;


import com.personal.project.entities.Environment;
import com.personal.project.services.EnvironmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Specie controller.
 */
@RestController
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(
            final EnvironmentService refEnvironmentService) {
        this.environmentService = refEnvironmentService;
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
