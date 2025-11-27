package com.personal.project.services;

import com.personal.project.model.Region;
import com.personal.project.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region(1L, "Region1");
    }

    @Test
    void findRegionById() {
        // Mock the behavior of the repository
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));

        // Call the method to test
        Optional<Region> foundRegion = regionService.findRegionById(1L);

        // Verify the result
        assertTrue(foundRegion.isPresent());
        assertEquals(region, foundRegion.get());
    }

    @Test
    void findRegionByIdFail() {
        // Mock the behavior of the repository
        when(regionRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test
        Optional<Region> foundRegion = regionService.findRegionById(1L);

        // Verify the result
        assertTrue(foundRegion.isEmpty());
    }

    @Test
    void findAllRegion() {
        // Create a list of species
        List<Region> regions = List.of(
                new Region(1L, "Region1"),
                new Region(2L, "Region2")
        );
        // Mock the behavior of the repository
        when(regionRepository.findAll()).thenReturn(regions);

        // Call the method to test
        Optional<List<Region>> foundRegions = regionService.findAllRegions();

        // Verify the result
        assertTrue(foundRegions.isPresent());
        assertEquals(2, foundRegions.get().size());
        assertEquals("Region1", foundRegions.get().get(0).getName());
        assertEquals("Region2", foundRegions.get().get(1).getName());
    }

    @Test
    void findAllRegionEmptyList() {
        // Mock the behavior of the repository
        when(regionRepository.findAll()).thenReturn(List.of());

        // Call the method to test
        Optional<List<Region>> foundRegions = regionService.findAllRegions();

        // Verify the result
        assertTrue(foundRegions.isPresent());
        assertEquals(0, foundRegions.get().size());
    }
}