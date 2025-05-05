package com.personal.project.controller.webLayer;

import com.personal.project.controller.SpecieController;
import com.personal.project.model.Specie;
import com.personal.project.services.SpecieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpecieController.class)
class SpecieControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecieService specieService;

    @Test
    void testGetAllSpeciesSuccess() throws Exception {
        Specie specie = new Specie(1L, "Lion");

        when(specieService.findAllSpecie())
                .thenReturn(Optional.of(List.of(specie)));

        mockMvc.perform(get("/species"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Lion"));
    }

    @Test
    void testGetAllSpeciesNotFound() throws Exception {
        when(specieService.findAllSpecie())
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/species"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetSpecieByIdSuccess() throws Exception {
        Specie specie = new Specie(42L, "Tiger");

        when(specieService.findSpecieById(42L))
                .thenReturn(Optional.of(specie));

        mockMvc.perform(get("/specie/42"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(42L))
                .andExpect(jsonPath("$.name").value("Tiger"));
    }

    @Test
    void testGetSpecieByIdNotFound() throws Exception {
        when(specieService.findSpecieById(99L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/specie/99"))
                .andExpect(status().isNotFound());
    }
}
