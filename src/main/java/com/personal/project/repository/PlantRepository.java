package com.personal.project.repository;

import com.personal.project.model.Plant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Plant repository.
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>,
                                        JpaSpecificationExecutor<Plant> {

    /** Find all plants by specification.
     * @param specification the specification
     * @return list of plants
     */
    List<Plant> findAll(Specification<Plant> specification);
}
