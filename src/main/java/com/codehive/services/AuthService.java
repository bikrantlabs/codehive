package com.codehive.services;

import com.codehive.domain.entity.User;
import com.codehive.exceptions.UserAlreadyExistsException;
import com.codehive.repository.ports.UserRepoInterface;
import com.codehive.utils.HashingService;

import java.sql.SQLException;

public class AuthService {
    private final UserRepoInterface userRepository;
    private final HashingService hashingService;

    public AuthService(UserRepoInterface userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
//        Hash the user's password
        user.setPassword(hashingService.hash(user.getPassword()));
        System.out.println("Password Hashed: " + user.getPassword());
        try {
            userRepository.create(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("users.email")) {
                throw new UserAlreadyExistsException();
            }
        }
    }
}
