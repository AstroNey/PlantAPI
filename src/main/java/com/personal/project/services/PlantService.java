package com.personal.project.services;

import com.personal.project.model.Plant;
import com.personal.project.model.request.PlantFilterRequest;
import com.personal.project.model.specification.PlantSpecification;
import com.personal.project.repository.PlantRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;


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

    public List<Plant> findAllPlantsByFilter(final PlantFilterRequest filter) {
        List<Specification<Plant>> specs = Stream.of(
                        match(filter.name(),     PlantSpecification::nameStartWith),
                        match(filter.idRegion(), PlantSpecification::hasRegion)
                )
                .filter(Objects::nonNull)
                .toList();

        if (specs.isEmpty()) {
            return plantRepository.findAll();
        }

        Specification<Plant> finalSpec = specs.stream().reduce(Specification.where(null), Specification::and);
        return plantRepository.findAll(finalSpec);
    }


    /**
     * Helper method to create a Specification if the value is not null.
     * @param value the value to check
     * @param function the function to create the Specification
     * @return the Specification or null
     * @param <T> the type of the value
     */
    private <T> Specification<Plant> match(T value, Function<T, Specification<Plant>> function) {
        return (value != null) ? function.apply(value) : null;
    }
}
