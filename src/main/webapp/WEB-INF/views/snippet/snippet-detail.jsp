<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <%@include file="../common/header.jsp" %>
    <link rel="stylesheet" href="../../../styles/snippet-detail.css"/>
    <link rel="stylesheet" href="../../../styles/navbar.css"/>


    <script defer>
        console.log("Hello world!")
        Prism.plugins.NormalizeWhitespace.setDefaults({
            'remove-trailing': true,
            'remove-indent': true,
            'left-trim': true,
            'right-trim': true
        });
    </script>
</head>
<body>
<div class="snippet-detail-container">
    <main class="main-content">
        <div class="container">
            <%@include file="../common/navbar.jsp" %>

            <div class="snippet-detail-section">

                <!-- Breadcrumb -->
                <nav class="breadcrumb">
                    <a href="${pageContext.request.contextPath}/snippets" class="breadcrumb-link">
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                             stroke-width="2">
                            <path d="M15 18l-6-6 6-6"/>
                        </svg>
                        Back to Snippets
                    </a>
                </nav>

                <!-- ✅ Show snippet if present -->
                <c:if test="${not empty snippet}">
                    <div class="snippet-header">
                        <div class="snippet-meta">
                            <div class="meta-left">
                                <span class="language-badge badge-${snippet.language}">${snippet.language}</span>
                                <div class="snippet-visibility">
                                    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                         stroke-width="2">
                                        <circle cx="12" cy="12" r="10"/>
                                        <path d="M2 12h20"/>
                                        <path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"/>
                                    </svg>
                                    <span class="visibility-text">
                                        <c:if test="${snippet.visible}">Public</c:if>
                                        <c:if test="${not snippet.visible}">Private</c:if>
                                    </span>
                                </div>
                            </div>
                            <div class="meta-right">
                                <span class="created-date">Created on ${snippet.createdAt}</span>
                            </div>
                        </div>
                        <h1 class="snippet-title">${snippet.title}</h1>
                    </div>

                    <div class="code-section">
                        <div class="code-header">
                            <div class="code-info">
                                <span class="code-language">${snippet.language}</span>
                            </div>
                            <div class="code-actions">
                                <button class="code-action-btn" onclick="copyCode('${snippet.id}')">Copy</button>
                                <button class="code-action-btn" onclick="downloadCode('${snippet.id}')">Download
                                </button>
                            </div>
                        </div>

                        <div class="code-content">
                            <pre data-download-link="${snippet.title}" data-id="${snippet.id}"><code
                                    data-id="${snippet.id}" class="language-${snippet.language}">
                                    ${fn:trim(snippet.content)}
                            </code></pre>
                        </div>
                    </div>
                </c:if>

                <!-- ❌ Show "Not Found" if snippet is null -->
                <c:if test="${empty snippet}">
                    <div class="not-found-section">
                        <div class="not-found-content">
                            <div class="not-found-text">
                                <h2 class="not-found-title">Snippet Not Found</h2>
                                <p class="not-found-description">
                                    The snippet may be private, deleted, or the link could be broken.
                                </p>
                                <div class="not-found-actions">
                                    <a href="/snippets" class="btn btn-primary">Browse Snippets</a>
                                    <a href="/new-snippet" class="btn btn-outline">Create New Snippet</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </main>
</div>
</body>
</html>
