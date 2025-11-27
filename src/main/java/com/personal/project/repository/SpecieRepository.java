package com.personal.project.repository;

import com.personal.project.model.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Specie repository.
 */
@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {
}
