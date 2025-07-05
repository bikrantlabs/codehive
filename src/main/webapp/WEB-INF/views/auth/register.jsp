<%--
  Created by IntelliJ IDEA.
  User: bjung
  Date: 7/1/25
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/header.jsp" %>
    <link rel="stylesheet" href="../../../styles/register.css"/>
</head>
<body>
<div class="register-container">
    <div class="register-card">
        <div class="register-header">
            <h1 class="register-title">Create Account</h1>
            <p class="register-subtitle">Join the Code Snippet Sharing Platform</p>
        </div>

        <form class="register-form" action="${pageContext.request.contextPath}/auth/register" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label for="username" class="form-label">Username</label>
                    <input
                            type="text"
                            id="username"
                            name="username"
                            value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
                            class="form-input"
                            placeholder="Choose a username"
                            required
                    />
                </div>

                <div class="form-group">
                    <label for="email" class="form-label">Email</label>
                    <input
                            type="email"
                            value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : ""  %>"
                            id="email"
                            name="email"
                            class="form-input"
                            placeholder="Enter your email"
                    <%--                            required--%>
                    />
                </div>

                <div class="form-group">
                    <label for="password" class="form-label">Password</label>
                    <input
                            type="password"
                            id="password"
                            name="password"
                            class="form-input"
                            placeholder="Create a password"
                            required
                    />
                </div>

                <div class="form-group">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input
                            type="password"
                            id="confirmPassword"
                            name="confirmPassword"
                            class="form-input"
                            placeholder="Confirm your password"
                    <%--                            required--%>
                    />
                </div>
                    <% if (request.getAttribute("error") != null) { %>
                <p style="color:red;"><%= request.getAttribute("error") %>
                </p>
                    <% } %>

                <button type="submit" class="btn btn-primary register-button">Create Account</button>
        </form>

        <div class="register-footer">
            <p class="signin-text">
                Already have an account?
                <a href="login.jsp" class="signin-link">Sign in</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
