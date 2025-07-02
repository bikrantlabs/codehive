package com.codehive.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class RequestUtil {
    public static void redirectWithError(
            HttpServletRequest request,
            HttpServletResponse response,
            String error,
            Map<String, String> formFields,
            String targetJsp
    ) throws ServletException, IOException {
        request.setAttribute("error", error);

        for (Map.Entry<String, String> entry : formFields.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        request.getRequestDispatcher(targetJsp).forward(request, response);
    }
}
