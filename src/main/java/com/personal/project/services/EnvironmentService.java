package com.personal.project.services;

import com.personal.project.model.Environment;
import com.personal.project.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Environment service.
 */
@Service
public class EnvironmentService {

    /**
     * Region repository.
     */
    private final EnvironmentRepository environmentRepository;

    /**
     * Constructor.
     * @param refEnvironmentRepository Environment repository.
     */
    public EnvironmentService(
            final EnvironmentRepository refEnvironmentRepository) {
        this.environmentRepository = refEnvironmentRepository;
    }

    /**
     * Find environment by id.
     * @param id Environment id.
     * @return Environment.
     */
    public Optional<Environment> findEnvironmentById(final Long id) {
        return environmentRepository.findById(id);
    }

    /**
     * Find all environment.
     * @return List of environment.
     */
    public Optional<List<Environment>> findAllEnvironment() {
        return Optional.of(environmentRepository.findAll());
    }
}
