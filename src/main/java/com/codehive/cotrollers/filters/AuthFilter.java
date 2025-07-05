package com.codehive.cotrollers.filters;

import com.codehive.domain.entity.Session;
import com.codehive.domain.entity.User;
import com.codehive.exceptions.InvalidSessionException;
import com.codehive.repository.SessionRepository;
import com.codehive.repository.UserRepository;
import com.codehive.repository.ports.SessionRepoInterface;
import com.codehive.repository.ports.UserRepoInterface;
import com.codehive.services.SessionService;
import com.codehive.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard", "/profile", "/snippet/new"})
public class AuthFilter implements Filter {

    private SessionService sessionService;
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
        UserRepoInterface userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
        SessionRepoInterface sessionRepo = new SessionRepository();
        this.sessionService = new SessionService(sessionRepo);
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String sessionId = extractSessionId(req);

        Session session = isValidSession(sessionId);
        if (sessionId == null || session == null) {
            redirectToLogin(req, res);
            return;
        }
// Session is valid till here
        User user = userService.getUserById(session.getUserId());

        if (user == null) {
            redirectToLogin(req, res);
            return;
        }
        req.setAttribute("user", user);
        
        chain.doFilter(servletRequest, servletResponse);
    }

    private String extractSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;

        for (Cookie cookie : cookies) {
            if ("SESSION_ID".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private Session isValidSession(String sessionId) {

        try {
            return sessionService.validateSession(sessionId);

        } catch (InvalidSessionException e) {
            return null;
        }
    }

    private void redirectToLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect(req.getContextPath() + "/auth/login");
    }
}
