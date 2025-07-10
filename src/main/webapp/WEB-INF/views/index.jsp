<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%@include file="common/header.jsp" %>
    <link rel="stylesheet" href="../../styles/home.css"/>

    <link rel="stylesheet" href="../../styles/navbar.css"/>
</head>
<body>
<div class="home-container">
    <!-- Header -->

    <%@include file="common/navbar.jsp" %>
    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <div class="hero-content">
                <h1 class="hero-title">Share Code Snippets with Developers Worldwide</h1>
                <p class="hero-description">
                    Discover, share, and collaborate on code snippets. Build your developer portfolio
                    and help others learn from your expertise.
                </p>
                <div class="hero-actions">
                    <c:if test="${user == null}">
                        <a href="auth/register" class="btn btn-primary btn-large">Get Started</a>
                    </c:if>
                    <c:if test="${user != null}">
                        <a href="snippets/create" class="btn btn-primary btn-large">View my Snippets</a>
                    </c:if>
                    <a href="snippets" class="btn btn-outline btn-large">Browse Snippets</a>
                </div>
                <div class="hero-stats">
                    <div class="stat">
                        <span class="stat-number">10K+</span>
                        <span class="stat-label">Code Snippets</span>
                    </div>
                    <div class="stat">
                        <span class="stat-number">5K+</span>
                        <span class="stat-label">Developers</span>
                    </div>
                    <div class="stat">
                        <span class="stat-number">50+</span>
                        <span class="stat-label">Languages</span>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Featured Snippets -->
  

    <!-- Features Section -->
    <section class="features">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Why Choose CodeShare?</h2>
                <p class="section-description">Everything you need to share and discover amazing code</p>
            </div>

            <div class="features-grid">
                <div class="feature-card card">
                    <div class="feature-icon">🚀</div>
                    <h3 class="feature-title">Fast & Easy</h3>
                    <p class="feature-description">Share your code snippets in seconds with our intuitive interface</p>
                </div>

                <div class="feature-card card">
                    <div class="feature-icon">🔍</div>
                    <h3 class="feature-title">Smart Search</h3>
                    <p class="feature-description">Find exactly what you need with our powerful search and filtering</p>
                </div>

                <div class="feature-card card">
                    <div class="feature-icon">👥</div>
                    <h3 class="feature-title">Community Driven</h3>
                    <p class="feature-description">Learn from other developers and contribute to the community</p>
                </div>

                <div class="feature-card card">
                    <div class="feature-icon">🎨</div>
                    <h3 class="feature-title">Syntax Highlighting</h3>
                    <p class="feature-description">Beautiful code presentation with support for 50+ languages</p>
                </div>
            </div>
        </div>
    </section>

    <!-- CTA Section -->
    <section class="cta">
        <div class="container">
            <div class="cta-content">
                <h2 class="cta-title">Ready to Start Sharing?</h2>
                <p class="cta-description">Join thousands of developers who are already sharing their knowledge</p>
                <div class="cta-actions">
                    <a href="auth/register" class="btn btn-primary btn-large">Create Account</a>
                    <a href="#" class="btn btn-outline btn-large">Learn More</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <div class="footer-content">
                <div class="footer-section">
                    <h3 class="footer-title">CodeShare</h3>
                    <p class="footer-description">The best platform for sharing and discovering code snippets</p>
                </div>

                <div class="footer-section">
                    <h4 class="footer-heading">Platform</h4>
                    <ul class="footer-links">
                        <li><a href="#" class="footer-link">Browse Snippets</a></li>
                        <li><a href="#" class="footer-link">Create Snippet</a></li>
                        <li><a href="#" class="footer-link">Community</a></li>
                        <li><a href="#" class="footer-link">API</a></li>
                    </ul>
                </div>

                <div class="footer-section">
                    <h4 class="footer-heading">Support</h4>
                    <ul class="footer-links">
                        <li><a href="#" class="footer-link">Help Center</a></li>
                        <li><a href="#" class="footer-link">Contact Us</a></li>
                        <li><a href="#" class="footer-link">Bug Reports</a></li>
                        <li><a href="#" class="footer-link">Feature Requests</a></li>
                    </ul>
                </div>

                <div class="footer-section">
                    <h4 class="footer-heading">Legal</h4>
                    <ul class="footer-links">
                        <li><a href="#" class="footer-link">Privacy Policy</a></li>
                        <li><a href="#" class="footer-link">Terms of Service</a></li>
                        <li><a href="#" class="footer-link">Cookie Policy</a></li>
                    </ul>
                </div>
            </div>

            <div class="footer-bottom">
                <p class="footer-copyright">© 2024 CodeShare. All rights reserved.</p>
                <div class="footer-social">
                    <a href="#" class="social-link">Twitter</a>
                    <a href="#" class="social-link">GitHub</a>
                    <a href="#" class="social-link">Discord</a>
                </div>
            </div>
        </div>
    </footer>
</div>
>
</body>
</html>
