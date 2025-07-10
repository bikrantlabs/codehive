package com.codehive.repository;

import com.codehive.db.DBConnection;
import com.codehive.domain.entity.Snippet;
import com.codehive.repository.ports.SnippetRepoInterface;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
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
            preparedStatement.setBoolean(5, snippet.isVisible());

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
        String sql = "SELECT * FROM snippets WHERE id = ?";
        PreparedStatement preparedStatement = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);

        ResultSet rows = preparedStatement.executeQuery();
        if (rows.next()) {
            return buildSnippetFromResultSet(rows);
        } else {
            return null; // or throw exception if you prefer
        }
    }

    @Override
    public List<Snippet> getAll(int start, int amount) throws SQLException {
        String query = "SELECT * FROM snippets LIMIT ? OFFSET ?";
        return getSnippets(start, amount, query);
    }

    @Override
    public List<Snippet> getAllByUserId(int userId, int start, int amount) throws SQLException {
        return List.of();
    }

    @Override
    public List<Snippet> getAllPublic(int start, int amount) throws SQLException {
        String query = "SELECT * FROM snippets  WHERE is_public = true LIMIT ? OFFSET ?";
        return getSnippets(start, amount, query);
    }

    @NotNull
    private List<Snippet> getSnippets(int start, int amount, String query) throws SQLException {
        try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, start);

            ResultSet resultSet = preparedStatement.executeQuery();
            return buildSnippetListFromResultSet(resultSet);
        }
    }

    private List<Snippet> buildSnippetListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Snippet> snippets = new ArrayList<>();
        while (resultSet.next()) {
            snippets.add(buildSnippetFromResultSet(resultSet));
        }
        return snippets;
    }

    private Snippet buildSnippetFromResultSet(ResultSet resultSet) throws SQLException {
        Snippet snippet = new Snippet();
        snippet.setId(resultSet.getInt("id"));
        snippet.setTitle(resultSet.getString("title"));
        snippet.setContent(resultSet.getString("content"));
        snippet.setLanguage(resultSet.getString("language"));
        snippet.setUserId(resultSet.getInt("user_id"));
        snippet.setVisible(resultSet.getBoolean("is_public"));
        snippet.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        snippet.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return snippet;
    }
}
