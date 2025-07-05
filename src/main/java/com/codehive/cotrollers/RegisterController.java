package com.codehive.cotrollers;


import com.codehive.domain.entity.User;
import com.codehive.exceptions.UserAlreadyExistsException;
import com.codehive.repository.SessionRepository;
import com.codehive.repository.UserRepository;
import com.codehive.services.AuthService;
import com.codehive.services.SessionService;
import com.codehive.services.ShaHashing;
import com.codehive.utils.HashingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/register")
public class RegisterController extends HttpServlet {
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
        HashingService hashingService = new ShaHashing();
        SessionRepository sessionRepo = new SessionRepository();
        SessionService sessionService = new SessionService(sessionRepo);

        User user = User.builder().username(username).password(password).email(email).build();

        AuthService authService = new AuthService(userRepo, hashingService, sessionService);

        try {
            authService.registerUser(user);
            response.sendRedirect(request.getContextPath() + "/auth/login");
        } catch (UserAlreadyExistsException e) {
            System.err.println("Exception: " + e.getMessage());
            redirectWithError(request, response, "User already exists", username, email);
        }
    }

    private void redirectWithError(HttpServletRequest request, HttpServletResponse response, String error, String username, String email) throws ServletException, IOException {
        request.setAttribute("error", error);
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }
}
