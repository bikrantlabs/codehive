package com.codehive.services;

import com.codehive.domain.entity.Snippet;
import com.codehive.repository.ports.SnippetRepoInterface;

import java.sql.SQLException;
import java.util.List;

public class SnippetService {
    private final SnippetRepoInterface snippetRepo;

    public SnippetService(SnippetRepoInterface snippetRepo) {
        this.snippetRepo = snippetRepo;
    }

    public Snippet create(Snippet snippet) throws SQLException {
        return snippetRepo.create(snippet);
    }

    public List<Snippet> getAllPublic(int start, int amount) throws SQLException {
        return snippetRepo.getAllPublic(start, amount);
    }
}
