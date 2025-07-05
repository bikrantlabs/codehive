package com.codehive.services;

import com.codehive.domain.entity.Snippet;
import com.codehive.repository.ports.SnippetRepoInterface;

import java.sql.SQLException;

public class SnippetService {
    private final SnippetRepoInterface snippetRepo;

    public SnippetService(SnippetRepoInterface snippetRepo) {
        this.snippetRepo = snippetRepo;
    }

    public Snippet create(Snippet snippet) throws SQLException {
        return snippetRepo.create(snippet);
    }
}
