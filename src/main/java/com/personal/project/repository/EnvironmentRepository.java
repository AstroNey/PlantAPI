package com.personal.project.repository;


import com.personal.project.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Environment repository.
 */
@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
}
