package com.personal.project.controller;

import com.personal.project.model.User;
import com.personal.project.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * User controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * Constructor.
     * @param refUserService User service.
     */
    public UserController(final UserService refUserService) {
        this.userService = refUserService;
    }

    /**
     * Get one user by id.
     * @param id the id
     * @return one user by id
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") final Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //TODO CRUD operations for User
}
