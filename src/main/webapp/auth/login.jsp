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
    <link rel="stylesheet" href="../styles/login.css"/>
</head>
<body>
<div class="login-container">
    <div class="login-card">
        <div class="login-header">
            <h1 class="login-title">Welcome Back</h1>
            <p class="login-subtitle">Sign in to Code Snippet Sharing Platform</p>
        </div>

        <form class="login-form" action="${pageContext.request.contextPath}/auth/login" method="post">
            <div class="form-group">
                <label for="email" class="form-label">Email</label>
                <input
                        type="email"
                        id="email"
                        name="email"
                        value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"

                        class="form-input"
                        placeholder="Enter your email"
                        required
                />
            </div>

            <div class="form-group">
                <label for="password" class="form-label">Password</label>
                <input

                        value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>"
                        type="password"
                        id="password"
                        name="password"
                        class="form-input"
                        placeholder="Enter your password"
                        required
                />
            </div>

            <div class="form-options">
                <div class="checkbox-group">
                    <input type="checkbox" id="remember" name="remember" class="form-checkbox"/>
                    <label for="remember" class="checkbox-label">Remember me</label>
                </div>
                <a href="#" class="forgot-password">Forgot password?</a>
            </div>

            <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %>
            </p>
            <% } %>


            <button type="submit" class="btn btn-primary login-button">Sign In</button>
        </form>

        <div class="login-footer">
            <p class="signup-text">
                Don't have an account?
                <a href="register.jsp" class="signup-link">Sign up</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
