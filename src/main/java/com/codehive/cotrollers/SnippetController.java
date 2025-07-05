package com.codehive.cotrollers;

import com.codehive.domain.entity.Snippet;
import com.codehive.domain.entity.User;
import com.codehive.repository.SnippetRepository;
import com.codehive.repository.ports.SnippetRepoInterface;
import com.codehive.services.SnippetService;
import com.codehive.utils.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/snippet/new")
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
                .isPublic(visibility.equals("public"))
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
        System.out.println("SNIPPET HIT");
        request.getRequestDispatcher("/WEB-INF/views/snippet/new-snippet.jsp").forward(request, response);
    }
}
