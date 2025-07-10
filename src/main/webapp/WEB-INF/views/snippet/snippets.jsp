<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: bjung
  Date: 7/5/25
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/header.jsp" %>
    <link rel="stylesheet" href="../../../styles/snippets.css"/>

    <link rel="stylesheet" href="../../../styles/navbar.css"/>
</head>
<body>
<div class="snippets-container">
    <main class="main-content">
        <div class="container">
            <%@include file="../common/navbar.jsp" %>

            <!-- Filters -->
            <div class="filters-section">
                <div class="filters-container">
                    <div class="filters-row">
                        <select class="form-select filter-select">
                            <option value="">All Languages</option>
                            <option value="javascript">JavaScript</option>
                            <option value="python">Python</option>
                            <option value="java">Java</option>
                            <option value="cpp">C++</option>
                            <option value="html">HTML</option>
                            <option value="css">CSS</option>
                            <option value="sql">SQL</option>
                        </select>

                        <select class="form-select filter-select">
                            <option value="newest">Newest First</option>
                            <option value="oldest">Oldest First</option>
                            <option value="popular">Most Popular</option>
                            <option value="title">Title A-Z</option>
                        </select>

                    </div>
                </div>
            </div>

            <!-- Snippets Grid -->
            <div class="snippets-grid">
                <!-- Sample Snippet Card -->
                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                <c:forEach var="snippet" items="${snippets}">
                    <article class="snippet-card">
                        <div class="snippet-header">
                            <div class="snippet-meta">
                                <span class="language-badge badge-${snippet.language}">${snippet.language}</span>
                                <div class="code-actions">
                                    <button class="code-action-btn" onclick="copyCode('${snippet.id}')">Copy</button>
                                    <button class="code-action-btn" onclick="downloadCode('${snippet.id}')">Download
                                    </button>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/snippet/${snippet.id}">
                                <h3 class="snippet-title">${snippet.title}</h3>
                            </a>
                        </div>

                        <div class="snippet-content">
                            <div class="code-preview">
                                <pre data-download-link="${snippet.title}" data-id="${snippet.id}"><code
                                        class="language-${snippet.language}"
                                        data-id="${snippet.id}">${snippet.content}</code></pre>
                            </div>
                        </div>

                        <div class="snippet-footer">
                            <div class="snippet-author">
                                <div class="author-avatar"></div>
                                <span class="author-name">bikrant</span>
                                <!-- Replace with user lookup if needed -->
                            </div>
                            <div class="snippet-stats">
                <span class="stat-item">
                        ${snippet.createdAt}
                </span>
                            </div>
                        </div>
                    </article>
                </c:forEach>

            </div>

            <!-- Load More -->
            <div class="load-more-section">
                <button class="btn btn-outline">Load More Snippets</button>
            </div>
        </div>
    </main>
</div>
<!-- Prism.js for syntax highlighting -->
<link href="../../../styles/prism.css" rel="stylesheet"/>
<script src="../../../styles/prism.js"></script>
</body>
</html>
