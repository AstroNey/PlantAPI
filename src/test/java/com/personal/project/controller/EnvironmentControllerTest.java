package com.personal.project.controller;

import com.personal.project.model.Environment;
import com.personal.project.services.EnvironmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EnvironmentControllerTest {

    @Mock
    private EnvironmentService environmentService;

    @InjectMocks
    private EnvironmentController environmentController;

    @Test
    void getEnvironmentByIdSuccess() {
        Environment environment = new Environment(1L, "Environment");
        when(environmentService.findEnvironmentById(1L)).thenReturn(Optional.of(environment));

        ResponseEntity<Environment> response = environmentController.getEnvironmentById(1L);

        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(environment, response.getBody());
    }

    @Test
    void getEnvironmentByIdNotFound() {
        when(environmentService.findEnvironmentById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Environment> response = environmentController.getEnvironmentById(1L);

        // Assert the response status and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllEnvironmentsSuccess() {
        Environment environment = new Environment(1L, "Environment");
        Environment environment2 = new Environment(2L, "Environment2");
        List<Environment> environments = new ArrayList<>();
        environments.add(environment);
        environments.add(environment2);

        when(environmentService.findAllEnvironment()).thenReturn(Optional.of(environments));

        ResponseEntity<List<Environment>> response = environmentController.getEnvironments();

        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(environments, response.getBody());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(environment, response.getBody().get(0));
        assertEquals(environment2, response.getBody().get(1));
    }

    @Test
    void getAllEnvironmentsNotFound() {
        when(environmentService.findAllEnvironment()).thenReturn(Optional.empty());

        ResponseEntity<List<Environment>> response = environmentController.getEnvironments();

        // Assert the response status and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
