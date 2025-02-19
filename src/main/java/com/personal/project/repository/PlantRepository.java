package com.personal.project.repository;

import com.personal.project.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Plant repository.
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    /**
     * Find all plant to use for limited request.
     * @param pageable the pageable to request a paged result.
     * @return the page with contents.
     */
    Page<Plant> findAll(Pageable pageable);
}
