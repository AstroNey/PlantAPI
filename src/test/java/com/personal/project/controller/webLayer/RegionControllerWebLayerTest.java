package com.personal.project.controller.webLayer;

import com.personal.project.controller.RegionController;
import com.personal.project.model.Region;
import com.personal.project.services.RegionService;
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

@WebMvcTest(RegionController.class)
class RegionControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    @Test
    void testGetAllRegionsSuccess() throws Exception {
        Region region = new Region(1L, "Europe");

        when(regionService.findAllRegions())
                .thenReturn(Optional.of(List.of(region)));

        mockMvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Europe"));
    }

    @Test
    void testGetAllRegionsNotFound() throws Exception {
        when(regionService.findAllRegions())
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/regions"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetRegionByIdSuccess() throws Exception {
        Region region = new Region(42L, "Africa");

        when(regionService.findRegionById(42L))
                .thenReturn(Optional.of(region));

        mockMvc.perform(get("/regions/42"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(42L))
                .andExpect(jsonPath("$.name").value("Africa"));
    }

    @Test
    void testGetRegionByIdNotFound() throws Exception {
        when(regionService.findRegionById(99L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/regions/99"))
                .andExpect(status().isNotFound());
    }
}