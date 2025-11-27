package com.personal.project.services;

import com.personal.project.model.Environment;
import com.personal.project.repository.EnvironmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnvironmentServiceTest {

    @Mock
    private EnvironmentRepository environmentRepository;

    @InjectMocks
    private EnvironmentService environmentService;

    private Environment environment;

    @BeforeEach
    void setUp() {
        environment = new Environment(1L, "Environment1");
    }

    @Test
    void findEnvironmentById() {
        // Mock the behavior of the repository
        when(environmentRepository.findById(1L)).thenReturn(Optional.of(environment));

        // Call the method to test
        Optional<Environment> foundEnvironment = environmentService.findEnvironmentById(1L);

        // Verify the result
        assertTrue(foundEnvironment.isPresent());
        assertEquals(environment, foundEnvironment.get());
    }

    @Test
    void findEnvironmentByIdFail() {
        // Mock the behavior of the repository
        when(environmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test
        Optional<Environment> foundEnvironment = environmentService.findEnvironmentById(1L);

        // Verify the result
        assertTrue(foundEnvironment.isEmpty());
    }

    @Test
    void findAllEnvironment() {
        // create a list of environments
        List<Environment> environments = List.of(
                new Environment(1L, "Environment1"),
                new Environment(2L, "Environment2")
        );

        // Mock the behavior of the repository
        when(environmentRepository.findAll()).thenReturn(environments);

        // Call the method to test
        Optional<List<Environment>> foundEnvironment = environmentService.findAllEnvironment();

        // Verify the result
        assertTrue(foundEnvironment.isPresent());
        assertEquals(2, foundEnvironment.get().size());
        assertEquals("Environment1", foundEnvironment.get().get(0).getName());
        assertEquals("Environment2", foundEnvironment.get().get(1).getName());
    }

    @Test
    void findAllEnvironmentEmptyList() {
        // Mock the behavior of the repository
        when(environmentRepository.findAll()).thenReturn(List.of());

        // Call the method to test
        Optional<List<Environment>> foundEnvironment = environmentService.findAllEnvironment();

        // Verify the result
        assertTrue(foundEnvironment.isPresent());
        assertEquals(0, foundEnvironment.get().size());
    }
}