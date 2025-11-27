package com.personal.project.services;

import com.password4j.Password;
import com.personal.project.exception.InvalidMailException;
import com.personal.project.model.User;
import com.personal.project.model.request.UserRequest;
import com.personal.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        User user = new User(1L, "name", "lastName", "email", "password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testGetUserByIdEmpty() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findUserById(1L);
        assertTrue(foundUser.isEmpty());
    }

    @Test
    void createUserShouldReturnUserWhenValidInput() {
        UserRequest request = new UserRequest("John", "Doe", "john.doe@example.com", "password123");
        User expectedUser = new User("John", "Doe", "john.doe@example.com", "encodedPass123");

        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User createdUser = userService.createUser(request);

        assertNotNull(createdUser);
        assertEquals(request.getName(), createdUser.getName());
        assertEquals(request.getLastname(), createdUser.getLastName());
        assertEquals(request.getEmail(), createdUser.getEmail());
        assertTrue(
                Password.check(request.getPassword(), createdUser.getPassword()).withArgon2()
        );

    }

    @Test
    void createUserShouldThrowInvalidMailExceptionWhenInvalidEmail() {
        UserRequest request = new UserRequest("John", "Doe", "invalid-email", "password123");
        assertThrows(InvalidMailException.class, () -> userService.createUser(request));

        UserRequest request2 = new UserRequest("John", "Doe", null,
                "password123");
        assertThrows(InvalidMailException.class, () -> userService.createUser(request2));

        UserRequest request3 = new UserRequest("John", "Doe", "",
                "password123");
        assertThrows(InvalidMailException.class, () -> userService.createUser(request3));

        UserRequest request4 = new UserRequest("John", "Doe", "",
                "password123");
        assertThrows(InvalidMailException.class, () -> userService.createUser(request4));

        UserRequest request5 = new UserRequest("John", "Doe", "   ",
                "password123");
        assertThrows(InvalidMailException.class, () -> userService.createUser(request5));
    }
}