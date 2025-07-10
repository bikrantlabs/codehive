package com.codehive.cotrollers.filters;

import com.codehive.domain.entity.Session;
import com.codehive.domain.entity.User;
import com.codehive.repository.UserRepository;
import com.codehive.repository.ports.UserRepoInterface;
import com.codehive.services.UserService;
import com.codehive.utils.SessionManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/auth/*") // Apply to all URLs
public class DisableAuthPagesFilter implements Filter {
    private UserService userService;
    private SessionManager sessionManager;


    @Override
    public void init(FilterConfig filterConfig) {
        UserRepoInterface userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
        this.sessionManager = new SessionManager();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Session session = sessionManager.getValidSession(req);
        if (session != null) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }
        if (session == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        User u = userService.getUserById(session.getUserId());

        if (u != null) {
            res.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if (u == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        req.setAttribute("user", u);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
