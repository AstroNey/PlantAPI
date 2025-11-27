package com.personal.project.services;

import com.personal.project.model.Specie;
import com.personal.project.repository.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecieService {

    private final SpecieRepository specieRepository;

    public SpecieService(final SpecieRepository refSpecieRepository) {
        this.specieRepository = refSpecieRepository;
    }

    public Optional<Specie> findSpecieById(final Long id) {
        return specieRepository.findById(id);
    }

    public Optional<List<Specie>> findAllSpecie() {
        return Optional.of(specieRepository.findAll());
    }
}
