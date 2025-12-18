package com.personal.project.controller.webLayer;

import com.personal.project.controller.EnvironmentController;
import com.personal.project.entities.Environment;
import com.personal.project.security.JwtAuthenticationFilter;
import com.personal.project.security.JwtService;
import com.personal.project.services.EnvironmentService;
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

@WebMvcTest(controllers = EnvironmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class EnvironmentControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvironmentService environmentService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void testGetAllEnvironmentsSuccess() throws Exception {
        Environment env = new Environment(1L, "Forest");

        when(environmentService.findAllEnvironment())
                .thenReturn(Optional.of(List.of(env)));

        mockMvc.perform(get("/environments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Forest"));
    }

    @Test
    void testGetAllEnvironmentsNotFound() throws Exception {
        when(environmentService.findAllEnvironment())
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/environments"))
                .andExpect(status().isNotFound());
    }
}
