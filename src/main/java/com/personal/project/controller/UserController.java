package com.personal.project.controller;

import com.personal.project.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    /**
     * User service.
     */
    private UserService userService;

    /**
     * Constructor.
     * @param userService User service.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
