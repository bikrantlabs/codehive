package com.codehive.repository.ports;

import com.codehive.domain.entity.Session;

import java.sql.SQLException;

public interface SessionRepoInterface {
    Session getSessionById(String sessionId) throws SQLException;

    Session getSessionByUserId(Integer userId) throws SQLException;

    Session createSession(Session session) throws SQLException;

    boolean deleteSession(Integer sessionId) throws SQLException;
}
