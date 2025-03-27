package com.personal.project.services;

import com.personal.project.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * User service.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor.
     * @param userRepository User repository.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
