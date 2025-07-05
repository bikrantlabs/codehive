package com.codehive.utils;

import com.codehive.domain.entity.Session;
import com.codehive.exceptions.InvalidSessionException;
import com.codehive.repository.SessionRepository;
import com.codehive.repository.ports.SessionRepoInterface;
import com.codehive.services.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class SessionManager {

    private final SessionService sessionService;

    public SessionManager() {
        SessionRepoInterface sessionRepo = new SessionRepository();
        this.sessionService = new SessionService(sessionRepo);
    }

    public Session getValidSession(HttpServletRequest request) {
        String sessionId = extractSessionId(request);
        if (sessionId == null) return null;

        try {
            return sessionService.validateSession(sessionId);
        } catch (InvalidSessionException e) {
            return null;
        }
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
}
