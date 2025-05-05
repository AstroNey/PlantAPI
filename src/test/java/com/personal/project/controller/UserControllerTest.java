package com.personal.project.controller;

import com.personal.project.model.User;
import com.personal.project.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUserByIdSuccess() {
        User user = new User(1L, "Name", "lastName", "Email", "Password");
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.findUserById(1L);

        // Assert the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUserByIdNotFound() {
        when(userService.findUserById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.findUserById(1L);

        // Assert the response status and body
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
