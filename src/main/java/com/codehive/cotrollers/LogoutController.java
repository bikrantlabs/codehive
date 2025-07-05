package com.codehive.cotrollers;


import com.codehive.utils.CookieUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log out the user
        System.out.println("LOGOUT HIT");
        //  Delete session cookie
        CookieUtils.deleteCookie(response, "SESSION_ID");
        // Redirect to the login page or home page after logout

        response.sendRedirect("/");
    }
}
