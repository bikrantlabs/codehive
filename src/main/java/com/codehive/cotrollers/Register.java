package com.codehive.cotrollers;


import com.codehive.domain.entity.User;
import com.codehive.exceptions.UserAlreadyExistsException;
import com.codehive.repository.ports.UserRepository;
import com.codehive.services.AuthService;
import com.codehive.services.ShaHashing;
import com.codehive.utils.HashingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");

        if (username == null || password == null || email == null || confirmPassword == null) {
            redirectWithError(request, response, "Invalid Input", username, email);
            return;
        }

        if (!password.equals(confirmPassword)) {
            redirectWithError(request, response, "Passwords do not match", username, email);
            return;
        }

        UserRepository userRepo = new UserRepository();
        User user = User.builder().username(username).password(password).email(email).build();

        HashingService hashingService = new ShaHashing();
        AuthService authService = new AuthService(userRepo, hashingService);

        try {
            authService.registerUser(user);
            response.sendRedirect("login.jsp");
        } catch (UserAlreadyExistsException e) {
            System.err.println("Exception: " + e.getMessage());
            redirectWithError(request, response, "User already exists", username, email);
        }
    }

    private void redirectWithError(HttpServletRequest request, HttpServletResponse response, String error, String username, String email) throws ServletException, IOException {
        request.setAttribute("error", error);
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
