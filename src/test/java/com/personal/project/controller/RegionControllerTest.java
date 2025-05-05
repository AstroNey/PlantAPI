package com.personal.project.controller;

import com.personal.project.model.Region;
import com.personal.project.services.RegionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegionControllerTest {

    @Mock
    private RegionService regionService;

    @InjectMocks
    private RegionController regionController;

    @Test
    void getRegionByIdSuccess() {
        Region region = new Region(1L, "Region");
        when(regionService.findRegionById(1L)).thenReturn(Optional.of(region));

        ResponseEntity<Region> response = regionController.getRegionById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(region, response.getBody());
    }

    @Test
    void getRegionByIdNotFound() {
        when(regionService.findRegionById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Region> response = regionController.getRegionById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllRegionsSuccess() {
        Region region = new Region(1L, "Region");
        Region region2 = new Region(2L, "Region2");
        List<Region> regions = new ArrayList<>();
        regions.add(region);
        regions.add(region2);

        when(regionService.findAllRegions()).thenReturn(Optional.of(regions));

        ResponseEntity<List<Region>> response = regionController.getRegions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(regions, response.getBody());
        assertEquals(regions.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(region, response.getBody().get(0));
        assertEquals(region2, response.getBody().get(1));
    }

    @Test
    void getAllRegionsNotFound() {
        when(regionService.findAllRegions()).thenReturn(Optional.empty());

        ResponseEntity<List<Region>> response = regionController.getRegions();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
