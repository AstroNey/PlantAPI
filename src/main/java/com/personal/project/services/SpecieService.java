package com.personal.project.services;

import com.personal.project.repository.SpecieRepository;
import org.springframework.stereotype.Service;

/**
 * Specie service.
 */
@Service
public class SpecieService {

    private final SpecieRepository specieRepository;

    /**
     * Constructor.
     * @param specieRepository Specie repository.
     */
    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }
}
