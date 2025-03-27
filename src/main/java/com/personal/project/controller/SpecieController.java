package com.personal.project.controller;


import com.personal.project.services.SpecieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Specie controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SpecieController {

    /**
     * Specie service.
     */
    private SpecieService specieService;

    /**
     * Constructor.
     * @param specieService Region service.
     */
    public SpecieController(SpecieService specieService) {
        this.specieService = specieService;
    }
}
