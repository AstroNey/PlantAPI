package com.personal.project.controller;


import com.personal.project.services.EnvironmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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
    public EnvironmentController(final EnvironmentService refEnvironmentService) {
        this.environmentService = refEnvironmentService;
    }
}
