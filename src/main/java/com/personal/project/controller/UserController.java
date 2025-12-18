package com.personal.project.controller;

import com.personal.project.entities.User;
import com.personal.project.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService refUserService) {
        this.userService = refUserService;
    }

    /**
     * Get one user by id.
     * @param id the id
     * @return one user by id
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(
            @PathVariable("id") final Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
