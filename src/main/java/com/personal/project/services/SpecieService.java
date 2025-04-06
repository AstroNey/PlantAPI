package com.personal.project.services;

import com.personal.project.model.Specie;
import com.personal.project.repository.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Specie service.
 */
@Service
public class SpecieService {

    /**
     * Specie repository.
     */
    private final SpecieRepository specieRepository;

    /**
     * Constructor.
     * @param refSpecieRepository Specie repository.
     */
    public SpecieService(final SpecieRepository refSpecieRepository) {
        this.specieRepository = refSpecieRepository;
    }


    /**
     * Find specie by id.
     * @param id Specie id.
     * @return Specie.
     */
    public Optional<Specie> findSpecieById(final Long id) {
        return specieRepository.findById(id);
    }

    /**
     * Find all specie.
     * @return List of specie.
     */
    public Optional<List<Specie>> findAllSpecie() {
        return Optional.of(specieRepository.findAll());
    }
}
