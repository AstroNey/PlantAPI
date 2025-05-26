package com.personal.project.services;

import com.personal.project.exception.InvalidMailException;
import com.personal.project.model.User;
import com.personal.project.model.requestModel.UserRequest;
import com.personal.project.repository.UserRepository;
import com.personal.project.tools.Tools;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User service.
 */
@Service
public class UserService {

    /**
     * User repository.
     */
    private final UserRepository userRepository;

    /**
     * Constructor.
     * @param refUserRepository User repository.
     */
    public UserService(final UserRepository refUserRepository) {
        this.userRepository = refUserRepository;
    }

    /**
     * Get one user by id.
     * @param id the id
     * @return one user by id
     */
    public Optional<User> findUserById(final Long id) {
        return userRepository.findById(id);
    }

    /**
     * Create user.
     * @param user the user
     * @return the created user
     */
    public User createUser(final UserRequest user) {
        if (!Tools.isValidEmail(user.getEmail())) {
            throw new InvalidMailException("Invalid email");
        }
        User newUser = new User(user.getName(), user.getLastname(),
                user.getEmail(), user.getPassword());

        userRepository.save(newUser);
        return newUser;
    }
}
