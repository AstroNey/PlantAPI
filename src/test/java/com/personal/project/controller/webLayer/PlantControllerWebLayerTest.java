package com.personal.project.controller.webLayer;

import com.personal.project.controller.PlantController;
import com.personal.project.model.Environment;
import com.personal.project.model.Plant;
import com.personal.project.model.Region;
import com.personal.project.model.Specie;
import com.personal.project.services.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
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

    private Plant testPlant;

    @BeforeEach
    void setUp() {
        Specie specie = new Specie(1L, "Specie");
        Environment environment = new Environment(1L, "Environment");
        Region region = new Region(1L, "Region");
        HashSet<Region> regions = new HashSet<>();
        regions.add(region);

        testPlant = new Plant.Builder()
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
    }

    @Test
    void getPlantByIdSuccess() throws Exception {
        when(plantService.findPlantById(1L)).thenReturn(Optional.of(testPlant));

        mockMvc.perform(get("/plants/{id}", testPlant.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getPlantByIdNotFound() throws Exception {
        when(plantService.findPlantById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/plants/999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getPlantsWithLimitSuccess() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);
        when(plantService.findPlantsWithLimit(pageable)).thenReturn(new PageImpl<>(List.of(testPlant), pageable, 1));

        mockMvc.perform(get("/plants")
                        .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }
}
