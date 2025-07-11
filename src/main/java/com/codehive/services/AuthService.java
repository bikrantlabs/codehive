package com.codehive.services;

import com.codehive.domain.entity.Session;
import com.codehive.domain.entity.User;
import com.codehive.exceptions.LoginException;
import com.codehive.exceptions.UserAlreadyExistsException;
import com.codehive.repository.ports.UserRepoInterface;
import com.codehive.utils.HashingService;

import java.sql.SQLException;

public class AuthService {
    private final UserRepoInterface userRepository;
    private final HashingService hashingService;
    private final SessionService sessionService;

    public AuthService(UserRepoInterface userRepository, HashingService hashingService, SessionService sessionService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.sessionService = sessionService;
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
//        Hash the user's password
        user.setPassword(hashingService.hash(user.getPassword()));
        try {
            userRepository.create(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("users.email")) {
                throw new UserAlreadyExistsException();
            }
        }
    }

    public String loginUser(User u) throws LoginException {
//        First get if user with email exists in database
        try {
            User user = userRepository.getByEmail(u.getEmail());
            if (user == null) {
                System.out.println(u.getEmail() + " not found");
                throw new LoginException();
            }
//            Compare incoming user's password with database hash password
            boolean passwordMatch = hashingService.verify(u.getPassword(), user.getPassword());
            if (!passwordMatch) {
                throw new LoginException();
            }
//            If user exists create session in database, etc
            Session session = sessionService.createSession(user);
            System.out.println("Login successful");
            return session.getSessionId();
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            throw new LoginException();
        }
    }
}
