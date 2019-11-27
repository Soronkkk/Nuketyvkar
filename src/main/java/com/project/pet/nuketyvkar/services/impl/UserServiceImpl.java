package com.project.pet.nuketyvkar.services.impl;

import com.project.pet.nuketyvkar.model.User;
import com.project.pet.nuketyvkar.repositories.UserRepository;
import com.project.pet.nuketyvkar.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
