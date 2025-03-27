package com.personal.project.controller;


import com.personal.project.services.RegionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Specie controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegionController {

    /**
     * Region service.
     */
    private RegionService regionService;

    /**
     * Constructor.
     * @param regionService Region service.
     */
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
}
