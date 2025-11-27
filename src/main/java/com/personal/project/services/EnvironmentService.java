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

    private final EnvironmentRepository environmentRepository;

    public EnvironmentService(
            final EnvironmentRepository refEnvironmentRepository) {
        this.environmentRepository = refEnvironmentRepository;
    }

    public Optional<Environment> findEnvironmentById(final Long id) {
        return environmentRepository.findById(id);
    }

    public Optional<List<Environment>> findAllEnvironment() {
        return Optional.of(environmentRepository.findAll());
    }
}
