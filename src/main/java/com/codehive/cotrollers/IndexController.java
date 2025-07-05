package com.codehive.cotrollers;

import com.codehive.domain.entity.Session;
import com.codehive.domain.entity.User;
import com.codehive.exceptions.InvalidSessionException;
import com.codehive.repository.SessionRepository;
import com.codehive.repository.UserRepository;
import com.codehive.repository.ports.UserRepoInterface;
import com.codehive.services.SessionService;
import com.codehive.services.UserService;
import com.codehive.utils.CookieUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("")
public class IndexController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("INDEX HIT");
        SessionRepository sessionRepository = new SessionRepository();
        SessionService sessionService = new SessionService(sessionRepository);
        String sessionId = CookieUtils.getCookie(request, "SESSION_ID");
        UserRepoInterface userRepo = new UserRepository();
        UserService userService = new UserService(userRepo);

        try {
            Session s = sessionService.validateSession(sessionId);
            User user = userService.getUserById(s.getUserId());
            request.setAttribute("user", user);
        } catch (InvalidSessionException e) {
            System.err.println("Invalid session: " + e.getMessage());
            request.setAttribute("user", null);
        }

        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
