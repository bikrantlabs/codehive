package com.codehive.repository;

import com.codehive.db.DBConnection;
import com.codehive.domain.entity.Session;
import com.codehive.repository.ports.SessionRepoInterface;

import java.sql.*;

public class SessionRepository implements SessionRepoInterface {
    private final Connection db;

    public SessionRepository() {
        this.db = DBConnection.getInstance().getConnection();
    }

    @Override
    public Session getSessionById(String id) throws SQLException {
        String query = "SELECT * FROM sessions WHERE session_id = ?";
        try (PreparedStatement ps = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setSessionId(rs.getString("session_id"));
                session.setUserId(rs.getInt("user_id"));
                session.setExpiresAt(rs.getTimestamp("expires_at").toLocalDateTime());
                session.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                session.setActive(rs.getBoolean("is_active"));
                return session;
            }
        }
        return null;
    }

    @Override
    public Session getSessionByUserId(Integer userId) throws SQLException {
        return null;
    }

    @Override
    public Session createSession(Session s) throws SQLException {
        String sql = "INSERT INTO sessions (session_id, user_id, expires_at) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, s.getSessionId());
            preparedStatement.setInt(2, s.getUserId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(s.getExpiresAt()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to create session");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int newId = resultSet.getInt(1);
//                    user.setId(newId);
                    s.setId(newId);
                    return s;
                } else {
                    throw new SQLException("Creating session failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public boolean deleteSession(Integer sessionId) throws SQLException {
        return false;
    }
}
