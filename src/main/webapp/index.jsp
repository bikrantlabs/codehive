<%--
  Created by IntelliJ IDEA.
  User: bjung
  Date: 7/1/25
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="dark">
<head>
    <%@include file="common/header.jsp" %>
    <link rel="stylesheet" href="styles/home.css"/>
</head>
<body>
<div class="home-container">
    <!-- Header -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <div class="logo">
                    <h2>CodeShare</h2>
                </div>
                <nav class="nav">
                    <a href="#" class="nav-link active">Home</a>
                    <a href="#" class="nav-link">Browse</a>
                    <a href="#" class="nav-link">Create</a>
                    <a href="#" class="nav-link">Community</a>
                </nav>
                <div class="header-actions">
                    <a href="auth/login.jsp" class="btn btn-outline">Sign In</a>
                    <a href="auth/register.jsp" class="btn btn-primary">Sign Up</a>
                </div>
            </div>
        </div>
    </header>

    <form action="reg" method="post">
        <div class="form-group">
            <label for="username" class="form-label">Username</label>
            <input
                    type="text"
                    id="username"
                    name="username"
                    class="form-input"
                    placeholder="Choose a username"
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
                    placeholder="Create a password"
                    required
            />
        </div>
        <button type="submit" class="btn btn-primary register-button">Create Account</button>


    </form>

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
                    <a href="auth/register.jsp" class="btn btn-primary btn-large">Get Started</a>
                    <a href="#" class="btn btn-outline btn-large">Browse Snippets</a>
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
    <section class="featured-snippets">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Featured Code Snippets</h2>
                <p class="section-description">Discover popular and trending code snippets from our community</p>
            </div>

            <div class="snippets-grid">
                <div class="snippet-card card">
                    <div class="card-header">
                        <div class="snippet-meta">
                            <span class="badge badge-primary">JavaScript</span>
                            <span class="snippet-date">2 days ago</span>
                        </div>
                        <h3 class="card-title">Async/Await Error Handling</h3>
                        <p class="card-description">Clean way to handle errors in async functions without try-catch
                            blocks</p>
                    </div>
                    <div class="card-content">
                        <div class="code-preview">
                            <code>const safeAsync = (fn) => (...args) => fn(...args).catch(err =>
                                console.error(err));</code>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="snippet-author">
                            <div class="author-avatar"></div>
                            <span class="author-name">@johndoe</span>
                        </div>
                        <div class="snippet-actions">
                            <button class="action-btn">
                                <span class="action-count">24</span>
                                ❤️
                            </button>
                            <button class="action-btn">
                                <span class="action-count">8</span>
                                💬
                            </button>
                        </div>
                    </div>
                </div>

                <div class="snippet-card card">
                    <div class="card-header">
                        <div class="snippet-meta">
                            <span class="badge">Python</span>
                            <span class="snippet-date">1 week ago</span>
                        </div>
                        <h3 class="card-title">List Comprehension with Conditions</h3>
                        <p class="card-description">Elegant way to filter and transform lists in Python</p>
                    </div>
                    <div class="card-content">
                        <div class="code-preview">
                            <code>squares = [x**2 for x in range(10) if x % 2 == 0]</code>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="snippet-author">
                            <div class="author-avatar"></div>
                            <span class="author-name">@pythonista</span>
                        </div>
                        <div class="snippet-actions">
                            <button class="action-btn">
                                <span class="action-count">18</span>
                                ❤️
                            </button>
                            <button class="action-btn">
                                <span class="action-count">5</span>
                                💬
                            </button>
                        </div>
                    </div>
                </div>

                <div class="snippet-card card">
                    <div class="card-header">
                        <div class="snippet-meta">
                            <span class="badge">CSS</span>
                            <span class="snippet-date">3 days ago</span>
                        </div>
                        <h3 class="card-title">Flexbox Centering</h3>
                        <p class="card-description">Perfect centering with flexbox - both horizontal and vertical</p>
                    </div>
                    <div class="card-content">
                        <div class="code-preview">
                            <code>.center { display: flex; justify-content: center; align-items: center; }</code>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="snippet-author">
                            <div class="author-avatar"></div>
                            <span class="author-name">@cssmaster</span>
                        </div>
                        <div class="snippet-actions">
                            <button class="action-btn">
                                <span class="action-count">32</span>
                                ❤️
                            </button>
                            <button class="action-btn">
                                <span class="action-count">12</span>
                                💬
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="section-footer">
                <a href="#" class="btn btn-outline">View All Snippets</a>
            </div>
        </div>
    </section>

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
                    <a href="register.html" class="btn btn-primary btn-large">Create Account</a>
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
