package com.personal.project.services;

import com.personal.project.model.Plant;
import com.personal.project.repository.PlantRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* Plant service.
*/
@Service
public class PlantService {

    /**
     * Plant repository.
     */
    private final PlantRepository plantRepository;

    /**
     * Constructor for PlantService.
     * @param refPlantRepository the plant repository
     */
    public PlantService(final PlantRepository refPlantRepository) {
        this.plantRepository = refPlantRepository;
    }

    /**
     * Get one plant by id.
     * @param id the id of the plant
     * @return one plant by id
     */
    public Optional<Plant> findPlantById(final Long id) {
        return plantRepository.findById(id);
    }

    /**
     * Get all plants without limits.
     * @return all plants.
     */
    public Iterable<Plant> findPlants() { return plantRepository.findAll(); }

    /**
     * Get all plants with limit.
     * @return the number of plant define by limit.
     */
    public Page<Plant> findPlantsWithLimit(org.springframework.data.domain.Pageable pageable) {
        return plantRepository.findAll(pageable);
    }
}
