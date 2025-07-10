package com.codehive.utils;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Routes {
    LOGIN("/auth/login.jsp"),
    REGISTER("/auth/register.jsp"),
    DASHBOARD("/dashboard.jsp"),
    HOME("/index.jsp"),
    SNIPPETS("/snippet/snippets.jsp"),
    SNIPPET_DETAIL("/snippet/snippet-detail.jsp"),
    NEW_SNIPPET("/snippet/new-snippet.jsp");

    private final String path;

    Routes(String path) {
        String basePath = "/WEB-INF/views";
        this.path = basePath + path;
    }

}
