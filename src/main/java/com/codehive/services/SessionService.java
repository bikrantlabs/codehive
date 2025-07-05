package com.codehive.services;

import com.codehive.domain.entity.Session;
import com.codehive.domain.entity.User;
import com.codehive.exceptions.InvalidSessionException;
import com.codehive.repository.ports.SessionRepoInterface;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;

public class SessionService {
    private final SessionRepoInterface sessionRepo;

    public SessionService(SessionRepoInterface sessionRepostiory) {
        this.sessionRepo = sessionRepostiory;
    }

    public Session validateSession(String sessionId) throws InvalidSessionException {
        try {
            if (sessionId == null) throw new InvalidSessionException();
            Session s = sessionRepo.getSessionById(sessionId);
            if (s == null) {
                throw new InvalidSessionException();
            }
            // Session is either inactive or expired
            if (s.isActive() && s.getExpiresAt().isAfter(LocalDateTime.now())) {
                return s;
            }
            return null;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw new InvalidSessionException();
        }
    }

    Session createSession(User user) {

        try {
            Session s = Session.builder().userId(user.getId()).sessionId(generateSessionId()).expiresAt(getSessionExpiryDate(7)).build();

            return sessionRepo.createSession(s);

        } catch (SQLException ex) {
//throw ex;
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private String generateSessionId() {
        byte[] bytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private LocalDateTime getSessionExpiryDate(int days) {
//        TODO Get date from days from now.
        return LocalDateTime.now().plusDays(days);
    }
}
