package com.personal.project.services;

import com.personal.project.repository.RegionRepository;
import org.springframework.stereotype.Service;

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
     * @param regionRepository Region repository.
     */
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

}
