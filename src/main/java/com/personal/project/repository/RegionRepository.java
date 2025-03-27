package com.personal.project.repository;

import com.personal.project.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Region repository.
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
