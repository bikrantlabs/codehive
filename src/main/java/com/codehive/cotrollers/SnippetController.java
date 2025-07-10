package com.codehive.cotrollers;

import com.codehive.domain.entity.Snippet;
import com.codehive.domain.entity.User;
import com.codehive.repository.SnippetRepository;
import com.codehive.repository.ports.SnippetRepoInterface;
import com.codehive.services.SnippetService;
import com.codehive.utils.RequestUtil;
import com.codehive.utils.Routes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/snippet/*")
public class SnippetController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String language = request.getParameter("language");
        String content = request.getParameter("content");
        String visibility = request.getParameter("visibility");


        Map<String, String> fields = Map.of(
                "title", title,
                "language", language,
                "content", content,
                "visibility", visibility
        );

        if (title.isEmpty() || language.isEmpty() || content.isEmpty() || visibility.isEmpty()) {
            RequestUtil.redirectWithError(request, response, "Invalid fields", fields, "/WEB-INF/views/snippet/new-snippet.jsp");
        }

        User user = (User) request.getAttribute("user");
        Snippet snippet = Snippet.builder()
                .title(title)
                .language(language)
                .content(content)
                .visible(visibility.equals("public"))
                .userId(user.getId())
                .build();

        SnippetRepoInterface snippetRepo = new SnippetRepository();
        SnippetService snippetService = new SnippetService(snippetRepo);
        try {
            snippetService.create(snippet);
            response.sendRedirect("/");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            RequestUtil.redirectWithError(request, response, "Failed to create snippet", fields, "/WEB-INF/views/snippet/new-snippet.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        System.out.println("Snippet Controller");
        if ("/new".equals(pathInfo)) {
            redirectToNewSnippet(request, response);
        } else {
            // Handle other paths if necessary
            handleSnippet(request, response);
        }
    }

    private void handleSnippet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String snippetId = request.getPathInfo().substring(1); // Remove leading slash
//        Try to parse snippetId to integer
        int id;
        try {
            id = Integer.parseInt(snippetId);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher(Routes.SNIPPETS.getPath()).forward(request, response);
            return;
        }

        SnippetRepoInterface snippetRepo = new SnippetRepository();
        SnippetService snippetService = new SnippetService(snippetRepo);

        try {
            Snippet snippet = snippetService.getById(id);
            request.setAttribute("snippet", snippet);
            request.getRequestDispatcher(Routes.SNIPPET_DETAIL.getPath()).forward(request, response);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            RequestUtil.redirectWithError(request, response, "Failed to fetch snippet", Map.of("snippetId", String.valueOf(id)), Routes.SNIPPETS.getPath());
        }


    }

    private void redirectToNewSnippet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Routes.NEW_SNIPPET.getPath()).forward(request, response);
    }
}
