package com.codehive.repository;

import com.codehive.db.DBConnection;
import com.codehive.domain.entity.User;
import com.codehive.repository.ports.UserRepoInterface;

import java.sql.*;

public class UserRepository implements UserRepoInterface {
    private final Connection db;

    public UserRepository() {
        db = DBConnection.getInstance().getConnection();
    }

    @Override
    public User create(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting user failed, no rows affected.");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int newId = resultSet.getInt(1);
                    user.setId(newId);
                    return user;
                } else {
                    throw new SQLException("Inserting user failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement preparedStatement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, email);

        ResultSet rows = preparedStatement.executeQuery();
        if (rows.next()) {
            User user = new User();
            user.setId(rows.getInt("id"));
            user.setEmail(rows.getString("email"));
            user.setUsername(rows.getString("username"));
            user.setPassword(rows.getString("password"));
            user.setCreatedAt(rows.getTimestamp("created_at").toLocalDateTime());
            user.setUpdatedAt(rows.getTimestamp("updated_at").toLocalDateTime());
            return user;
        }

        return null;
    }
}
