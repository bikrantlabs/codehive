package com.codehive.cotrollers.filters;

import com.codehive.domain.entity.Session;
import com.codehive.exceptions.InvalidSessionException;
import com.codehive.repository.SessionRepository;
import com.codehive.repository.ports.SessionRepoInterface;
import com.codehive.services.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard", "/profile"})
public class AuthFilter implements Filter {

    private SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) {
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

        if (sessionId == null || !isValidSession(sessionId)) {
            redirectToLogin(res);
            return;
        }

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

    private boolean isValidSession(String sessionId) {

        try {
            Session s = sessionService.validateSession(sessionId);
            return s != null && s.getUserId() != null;
        } catch (InvalidSessionException e) {
            return false;
        }
    }

    private void redirectToLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("/auth/login.jsp");
    }
}
