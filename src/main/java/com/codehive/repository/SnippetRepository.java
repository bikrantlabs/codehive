package com.codehive.repository;

import com.codehive.db.DBConnection;
import com.codehive.domain.entity.Snippet;
import com.codehive.repository.ports.SnippetRepoInterface;

import java.sql.*;
import java.util.List;

public class SnippetRepository implements SnippetRepoInterface {

    private final Connection db;

    public SnippetRepository() {
        this.db = DBConnection.getInstance().getConnection();
    }

    @Override
    public Snippet create(Snippet snippet) throws SQLException {
        String sql = "INSERT INTO snippets (title, content, language, user_id, is_public) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, snippet.getTitle());
            preparedStatement.setString(2, snippet.getContent());
            preparedStatement.setString(3, snippet.getLanguage());
            preparedStatement.setInt(4, snippet.getUserId());
            preparedStatement.setBoolean(5, snippet.isPublic());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting snippet failed, no rows affected.");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int newId = resultSet.getInt(1);
                    snippet.setId(newId);
                    return snippet;
                } else {
                    throw new SQLException("Inserting snippet failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public Snippet getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Snippet> getAll(int start, int amount) throws SQLException {
        return List.of();
    }
}
