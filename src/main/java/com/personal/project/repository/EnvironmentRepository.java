package com.personal.project.repository;


import com.personal.project.model.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository
        extends JpaRepository<Environment, Long> {
}
