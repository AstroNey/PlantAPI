package com.personal.project.services;

import com.personal.project.model.Plant;
import com.personal.project.model.specification.PlantSpecification;
import com.personal.project.repository.PlantRepository;
import org.springframework.data.jpa.domain.Specification;
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
     * Get all plants.
     * @return all plants
     */
    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }


    /**
     * Get all plants by filter.
     * @param name the name of the plant
     * @param idRegion the id of the region
     * @return all plants by filter
     */
    public List<Plant> findAllPlantsByFilter(
            final String name,
            final long idRegion
    ) {
        Specification<Plant> specification = Specification
                .where(PlantSpecification.nameStartWith(name))
                .and(PlantSpecification.hasRegion(idRegion));
        return plantRepository.findAll(specification);
    }
}
