package com.personal.project.services;

import com.personal.project.exception.InvalidMailException;
import com.personal.project.model.User;
import com.personal.project.model.request.UserRequest;
import com.personal.project.repository.UserRepository;
import com.personal.project.tools.Tools;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository refUserRepository) {
        this.userRepository = refUserRepository;
    }

    public Optional<User> findUserById(final Long id) {
        return userRepository.findById(id);
    }

    public User createUser(final UserRequest user) {
        if (!Tools.isValidEmail(user.getEmail())) {
            throw new InvalidMailException("Invalid email");
        }
        User newUser = new User(user.getUsername(), user.getLastname(),
                user.getEmail(), user.getPassword());

        userRepository.save(newUser);
        return newUser;
    }
}
