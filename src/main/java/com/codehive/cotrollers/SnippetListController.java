package com.codehive.cotrollers;

import com.codehive.domain.entity.Snippet;
import com.codehive.repository.SnippetRepository;
import com.codehive.repository.ports.SnippetRepoInterface;
import com.codehive.services.SnippetService;
import com.codehive.utils.Routes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/snippets")
public class SnippetListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SNIPPET LIST HIT");
        SnippetRepoInterface snippetRepo = new SnippetRepository();
        SnippetService snippetService = new SnippetService(snippetRepo);


        int[] params = getAndParseParams(request);

        try {
            List<Snippet> snippets = snippetService.getAllPublic(params[0], params[1]);
            System.out.println("Snippets fetched: " + snippets.size());
            for (Snippet snippet : snippets) {
                System.out.println("Snippet ID: " + snippet.getId() + ", Title: " + snippet.getTitle() + ", Language: " + snippet.getLanguage());
            }
            request.getSession().setAttribute("snippets", snippets);
            request.getRequestDispatcher(Routes.SNIPPETS.getPath()).forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] getAndParseParams(HttpServletRequest req) {
        int start = 0;
        int limit = 10;

        try {
            start = Integer.parseInt(req.getParameter("start"));
        } catch (Exception ignored) {
        }

        try {
            limit = Integer.parseInt(req.getParameter("limit"));
        } catch (Exception ignored) {
        }

        return new int[]{start, limit};
    }
}
