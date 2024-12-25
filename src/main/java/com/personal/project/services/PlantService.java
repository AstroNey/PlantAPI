package com.personal.project.services;

import com.personal.project.model.Plant;
import com.personal.project.repository.PlantRepository;
import io.micrometer.observation.ObservationFilter;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
