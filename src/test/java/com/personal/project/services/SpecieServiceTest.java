package com.personal.project.services;

import com.personal.project.model.Specie;
import com.personal.project.repository.SpecieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecieServiceTest {

    @Mock
    private SpecieRepository specieRepository;

    @InjectMocks
    private SpecieService specieService;

    private Specie specie;

    @BeforeEach
    void setUp() {
        specie = new Specie(1L, "Specie1");
    }

    @Test
    void findSpecieById() {
        // Mock the behavior of the repository
        when(specieRepository.findById(1L)).thenReturn(Optional.of(specie));

        // Call the method to test
        Optional<Specie> foundSpecie = specieService.findSpecieById(1L);

        // Verify the result
        assertTrue(foundSpecie.isPresent());
        assertEquals(specie, foundSpecie.get());
    }

    @Test
    void findSpecieByIdFail() {
        // Mock the behavior of the repository
        when(specieRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test
        Optional<Specie> foundSpecie = specieService.findSpecieById(1L);

        // Verify the result
        assertTrue(foundSpecie.isEmpty());
    }

    @Test
    void findAllSpecie() {
        // Create a list of species
        List<Specie> species = List.of(
                new Specie(1L, "Specie1"),
                new Specie(2L, "Specie2")
        );
        // Mock the behavior of the repository
        when(specieRepository.findAll()).thenReturn(species);

        // Call the method to test
        Optional<List<Specie>> foundSpecies = specieService.findAllSpecie();

        // Verify the result
        assertTrue(foundSpecies.isPresent());
        assertEquals(2, foundSpecies.get().size());
        assertEquals("Specie1", foundSpecies.get().get(0).getName());
        assertEquals("Specie2", foundSpecies.get().get(1).getName());
    }

    @Test
    void findAllSpecieEmptyList() {
        // Mock the behavior of the repository
        when(specieRepository.findAll()).thenReturn(List.of());

        // Call the method to test
        Optional<List<Specie>> foundSpecies = specieService.findAllSpecie();

        // Verify the result
        assertTrue(foundSpecies.isPresent());
        assertEquals(0, foundSpecies.get().size());
    }
}