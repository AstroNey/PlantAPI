package com.personal.project.services;

import com.personal.project.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

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
     * @param environmentRepository Environment repository.
     */
    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }
}
