package com.personal.project.controller.webLayer;

import com.personal.project.controller.PlantController;
import com.personal.project.entities.Environment;
import com.personal.project.entities.Plant;
import com.personal.project.entities.Region;
import com.personal.project.entities.Specie;
import com.personal.project.entities.builders.PlantBuilder;
import com.personal.project.enums.LightLevel;
import com.personal.project.enums.Watering;
import com.personal.project.security.JwtAuthenticationFilter;
import com.personal.project.security.JwtService;
import com.personal.project.services.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlantControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantService plantService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private UserDetailsService userDetailsService;

    private Plant testPlant;

    @BeforeEach
    void setUp() {
        Specie specie = new Specie(1L, "Specie");
        Environment environment = new Environment(1L, "Environment");
        Region region = new Region(1L, "Region");
        HashSet<Region> regions = new HashSet<>();
        regions.add(region);

        testPlant = new PlantBuilder()
                .setId(1L)
                .setScientificName("ScientificName")
                .setName("Name")
                .setFoliage("Foliage")
                .setFlowers("Flowers")
                .setSize(0.6)
                .setSunlight(LightLevel.MEDIUM)
                .setWatering(Watering.AQUATIC)
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
    void getAllPlants_success() throws Exception {
        when(plantService.findAllPlants()).thenReturn(List.of(testPlant));

        mockMvc.perform(get("/plants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void getAllPlants_notFound() throws Exception {
        when(plantService.findAllPlants()).thenReturn(List.of());

        mockMvc.perform(get("/plants"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllPlantsByFilter_success() throws Exception {
        when(plantService.findAllPlantsByFilter(any()))
                .thenReturn(List.of(testPlant));

        mockMvc.perform(get("/plants/filter")
                        .param("hasLightLevel", "MEDIUM"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void getAllPlantsByFilter_notFound() throws Exception {
        when(plantService.findAllPlantsByFilter(any()))
                .thenReturn(List.of());

        mockMvc.perform(get("/plants/filter"))
                .andExpect(status().isNotFound());
    }
}
