package com.codehive.cotrollers;

import com.codehive.domain.entity.User;
import com.codehive.exceptions.LoginException;
import com.codehive.repository.SessionRepository;
import com.codehive.repository.UserRepository;
import com.codehive.services.AuthService;
import com.codehive.services.SessionService;
import com.codehive.services.ShaHashing;
import com.codehive.utils.CookieUtils;
import com.codehive.utils.HashingService;
import com.codehive.utils.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Map<String, String> fields = Map.of(
                "email", email,
                "password", password
        );

        if (email.isEmpty() || password.isEmpty()) {
            RequestUtil.redirectWithError(request, response, "Invalid fields", fields, "login.jsp");
            return;
        }

        UserRepository userRepo = new UserRepository();
        SessionRepository sessionRepo = new SessionRepository();
        HashingService hashingService = new ShaHashing();
        SessionService sessionService = new SessionService(sessionRepo);
        User user = User.builder().password(password).email(email).build();

        AuthService authService = new AuthService(userRepo, hashingService, sessionService);
        try {
            String sessionId = authService.loginUser(user);
            CookieUtils.setCookie(response, "SESSION_ID", sessionId);
            response.sendRedirect("/");

        } catch (LoginException e) {
            System.err.println("Exception: " + e.getMessage());
            RequestUtil.redirectWithError(request, response, e.getMessage(), fields, "login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }
}
