package com.personal.project.config;

import com.personal.project.security.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    void publicPlantsEndpoint_isAccessible() throws Exception {
        mockMvc.perform(get("/plants"))
                .andExpect(status().isOk());
    }

    @Test
    void publicPlantsById_isAccessible() throws Exception {
        mockMvc.perform(get("/plants/1"))
                .andExpect(status().isOk());
    }

    @Test
    void publicAuthEndpoint_isAccessible() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content("{\"username\":\"test\",\"password\":\"test\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void publicAuthRegister_isAccessible() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content("{\"username\":\"test\",\"email\":\"t@t.com\",\"password\":\"test\"}"))
                .andExpect(status().isOk());
    }
}

