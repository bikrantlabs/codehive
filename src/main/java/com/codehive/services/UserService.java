package com.codehive.services;

import com.codehive.domain.entity.User;
import com.codehive.repository.ports.UserRepoInterface;

import java.sql.SQLException;

public class UserService {
    private final UserRepoInterface userRepo;

    public UserService(UserRepoInterface userRepo) {
        this.userRepo = userRepo;
    }

    // Add methods to interact with userRepo, e.g., createUser, getUserById, etc.
    public void createUser(String username, String email) {
        // Implementation for creating a user
        // This is just a placeholder; actual implementation will depend on UserRepoInterface methods
    }

    public User getUserById(int userId) {
        try {
            return userRepo.getById(userId);
        } catch (SQLException e) {
//            e.printStackTrace();
            System.err.println("User with ID " + userId + " not found: " + e.getMessage());
            return null; // Handle exception appropriately
        }
    }
}
