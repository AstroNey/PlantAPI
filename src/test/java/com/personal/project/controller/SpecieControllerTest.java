package com.personal.project.controller;

import com.personal.project.model.Specie;
import com.personal.project.services.SpecieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpecieControllerTest {

    @Mock
    private SpecieService specieService;

    @InjectMocks
    private SpecieController specieController;

    @Test
    void getSpecieByIdSuccess() {
        Specie specie = new Specie(1L, "Specie");
        when(specieService.findSpecieById(1L)).thenReturn(Optional.of(specie));

        ResponseEntity<Specie> response = specieController.getSpecieById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(specie, response.getBody());
    }

    @Test
    void getSpecieByIdNotFound() {
        when(specieService.findSpecieById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Specie> response = specieController.getSpecieById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllSpeciesSuccess() {
        Specie specie = new Specie(1L, "Specie");
        Specie specie2 = new Specie(2L, "Specie2");
        List<Specie> species = new ArrayList<>();
        species.add(specie);
        species.add(specie2);

        when(specieService.findAllSpecie()).thenReturn(Optional.of(species));

        ResponseEntity<List<Specie>> response = specieController.getSpecies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(species, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals(specie, response.getBody().get(0));
        assertEquals(specie2, response.getBody().get(1));
    }

    @Test
    void getAllSpeciesNotFound() {
        when(specieService.findAllSpecie()).thenReturn(Optional.empty());

        ResponseEntity<List<Specie>> response = specieController.getSpecies();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
