package com.personal.project.services;

import com.personal.project.model.Region;
import com.personal.project.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Region service.
 */
@Service
public class RegionService {

    /**
     * Region repository.
     */
    private final RegionRepository regionRepository;

    /**
     * Constructor.
     * @param refRegionRepository Region repository.
     */
    public RegionService(final RegionRepository refRegionRepository) {
        this.regionRepository = refRegionRepository;
    }

    /**
     * Find region by id.
     * @param id Region id.
     * @return Region.
     */
    public Optional<Region> findRegionById(final Long id) {
        return regionRepository.findById(id);
    }

    /**
     * Find all regions.
     * @return List of regions.
     */
    public Optional<List<Region>> findAllRegions() {
        return Optional.of(regionRepository.findAll());
    }
}
