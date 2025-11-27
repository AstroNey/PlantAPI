package com.personal.project.services;

import com.personal.project.model.User;
import com.personal.project.repository.UserRepository;
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
}
