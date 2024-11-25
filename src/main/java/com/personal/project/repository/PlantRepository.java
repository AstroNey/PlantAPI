package com.personal.project.repository;

import com.personal.project.model.Plant;
import org.springframework.data.repository.CrudRepository;

/**
 * Plant repository.
 */
public interface PlantRepository extends CrudRepository<Plant, Long> {
}
