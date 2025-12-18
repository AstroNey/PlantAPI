package com.personal.project.controller;


import com.personal.project.entities.Region;
import com.personal.project.services.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RegionController {

    private final RegionService regionService;

    public RegionController(final RegionService refRegionService) {
        this.regionService = refRegionService;
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
                .map(regions ->
                        ResponseEntity.ok(regions.stream().toList()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
