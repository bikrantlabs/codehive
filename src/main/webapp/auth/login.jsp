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

        <form class="login-form">
            <div class="form-group">
                <label for="email" class="form-label">Email</label>
                <input
                        type="email"
                        id="email"
                        name="email"
                        class="form-input"
                        placeholder="Enter your email"
                        required
                />
            </div>

            <div class="form-group">
                <label for="password" class="form-label">Password</label>
                <input
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

            <button type="submit" class="btn btn-primary login-button">Sign In</button>

            <div class="divider">
                <span class="divider-text">or</span>
            </div>

            <button type="button" class="btn btn-secondary google-button">
                <svg class="google-icon" viewBox="0 0 24 24" width="20" height="20">
                    <path fill="#4285F4"
                          d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"></path>
                    <path fill="#34A853"
                          d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"></path>
                    <path fill="#FBBC05"
                          d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"></path>
                    <path fill="#EA4335"
                          d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"></path>
                </svg>
                Continue with Google
            </button>
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
