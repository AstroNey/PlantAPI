package com.personal.project.services;

import com.personal.project.model.Region;
import com.personal.project.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(final RegionRepository refRegionRepository) {
        this.regionRepository = refRegionRepository;
    }

    public Optional<Region> findRegionById(final Long id) {
        return regionRepository.findById(id);
    }

    public Optional<List<Region>> findAllRegions() {
        return Optional.of(regionRepository.findAll());
    }
}
