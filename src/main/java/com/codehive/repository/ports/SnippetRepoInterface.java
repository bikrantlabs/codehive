package com.codehive.repository.ports;

import com.codehive.domain.entity.Snippet;

import java.sql.SQLException;
import java.util.List;

public interface SnippetRepoInterface {
    Snippet create(Snippet snippet) throws SQLException;

    Snippet getById(Integer id) throws SQLException;

    List<Snippet> getAll(int start, int amount) throws SQLException;

    List<Snippet> getAllByUserId(int userId, int start, int amount) throws SQLException;

    List<Snippet> getAllPublic(int start, int amount) throws SQLException;
}
