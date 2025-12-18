package com.personal.project.controller.webLayer;

import com.personal.project.controller.RegionController;
import com.personal.project.entities.Region;
import com.personal.project.security.JwtAuthenticationFilter;
import com.personal.project.security.JwtService;
import com.personal.project.services.RegionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegionController.class)
@AutoConfigureMockMvc(addFilters = false)
class RegionControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private UserDetailsService userDetailsService;


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
}