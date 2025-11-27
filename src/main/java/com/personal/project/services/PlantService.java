package com.personal.project.services;

import com.personal.project.model.Plant;
import com.personal.project.model.specification.PlantSpecification;
import com.personal.project.repository.PlantRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(final PlantRepository refPlantRepository) {
        this.plantRepository = refPlantRepository;
    }

    public Optional<Plant> findPlantById(final Long id) {
        return plantRepository.findById(id);
    }

    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

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
