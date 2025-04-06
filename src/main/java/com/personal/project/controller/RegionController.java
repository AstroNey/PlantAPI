package com.personal.project.controller;


import com.personal.project.model.Region;
import com.personal.project.services.RegionService;
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
public class RegionController {

    /**
     * Region service.
     */
    private RegionService regionService;

    /**
     * Constructor.
     * @param refRegionService Region service.
     */
    public RegionController(final RegionService refRegionService) {
        this.regionService = refRegionService;
    }

    /**
     * Get one region by id.
     * @param id the id
     * @return one region by id
     */
    @GetMapping("/regions/{id}")
    public ResponseEntity<Region> getRegionById(
            @PathVariable("id") final Long id
    ) {
        return regionService.findRegionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all regions if data exist.
     * @return Get all regions
     */
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions() {
        Optional<List<Region>> optRegions = regionService.findAllRegions();
        return optRegions
                .map(ArrayList::new)
                .map(regions -> {
                    return ResponseEntity.ok(regions.stream().toList());
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
