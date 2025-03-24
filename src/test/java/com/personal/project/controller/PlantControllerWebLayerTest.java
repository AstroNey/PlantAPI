package com.personal.project.controller;

import com.personal.project.model.Environment;
import com.personal.project.model.Plant;
import com.personal.project.model.Region;
import com.personal.project.model.Specie;
import com.personal.project.services.PlantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantController.class)
class PlantControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantService plantService;

    @Test
    void getPlantByIdSuccess() throws Exception {
        Specie specie = new Specie(1L, "Specie");
        Environment environment = new Environment(1L, "Environment");
        Region region = new Region(1L, "Region");
        HashSet<Region> regions = new HashSet<>();
        regions.add(region);

        Plant plant = new Plant.Builder()
                .setId(1L)
                .setScientificName("ScientificName")
                .setName("Name")
                .setDescription("Description")
                .setFoliage("Foliage")
                .setFlowers("Flowers")
                .setSize(0.6)
                .setSunlight("Sunlight")
                .setWatering("Watering")
                .setSoil("Soil")
                .setTemperature("Temp")
                .setCare("Care")
                .setToxicity("Toxicity")
                .setImage("Image")
                .setEnvironment(environment)
                .setSpecie(specie)
                .setRegions(regions)
                .build();
        when(plantService.findPlantById(1L)).thenReturn(Optional.of(plant));

        mockMvc.perform(get("/plants/{id}", plant.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getPlantByIdNotFound() throws Exception {
        when(plantService.findPlantById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/plants/999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
