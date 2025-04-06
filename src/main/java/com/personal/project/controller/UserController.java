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
     * @param refUserService User service.
     */
    public UserController(final UserService refUserService) {
        this.userService = refUserService;
    }
}
