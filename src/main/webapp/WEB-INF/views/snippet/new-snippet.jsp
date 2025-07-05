<%--
  Created by IntelliJ IDEA.
  User: bjung
  Date: 7/5/25
  Time: 6:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/header.jsp" %>
    <link rel="stylesheet" href="../../../styles/new-snippet.css"/>
</head>
<body>
<div class="new-snippet-container">
    <!-- Main Content -->
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h1 class="page-title">Create New Snippet</h1>
                <p class="page-description">Share your code with the developer community</p>
            </div>

            <div class="snippet-form-container">
                <form class="snippet-form" id="snippetForm" action="${pageContext.request.contextPath}/snippet/new"
                      method="post">
                    <!-- Basic Information -->
                    <div class="form-section">
                        <div class="form-group">
                            <label for="title" class="form-label">Title *</label>
                            <input
                                    type="text"
                                    id="title"
                                    name="title"
                                    class="form-input"
                                    placeholder="Enter a descriptive title for your snippet"
                                    required
                            />
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="language" class="form-label">Language *</label>
                                <select id="language" name="language" class="form-select" required>
                                    <option value="">Select a language</option>
                                    <option value="javascript">JavaScript</option>
                                    <option value="python">Python</option>
                                    <option value="java">Java</option>
                                    <option value="cpp">C++</option>
                                    <option value="c">C</option>
                                    <option value="csharp">C#</option>
                                    <option value="php">PHP</option>
                                    <option value="ruby">Ruby</option>
                                    <option value="go">Go</option>
                                    <option value="rust">Rust</option>
                                    <option value="typescript">TypeScript</option>
                                    <option value="html">HTML</option>
                                    <option value="css">CSS</option>
                                    <option value="scss">SCSS</option>
                                    <option value="sql">SQL</option>
                                    <option value="bash">Bash</option>
                                    <option value="powershell">PowerShell</option>
                                    <option value="json">JSON</option>
                                    <option value="xml">XML</option>
                                    <option value="yaml">YAML</option>
                                    <option value="markdown">Markdown</option>
                                    <option value="other">Other</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <!-- Code Input -->
                    <div class="form-section">
                        <div class="tab-content">
                            <!-- Paste Code Tab -->
                            <div class="tab-panel active" id="paste-panel">
                                <div class="form-group">
                                    <label for="code" class="form-label">Code *</label>
                                    <textarea
                                            id="code"
                                            name="content"
                                            class="form-textarea code-textarea"
                                            placeholder="Paste your code here..."
                                            rows="15"
                                            required
                                    ></textarea>
                                </div>
                            </div>

                            <!-- Upload File Tab -->
                        </div>
                    </div>

                    <!-- Privacy Settings -->
                    <div class="form-section">
                        <h3 class="section-title">Privacy & Sharing</h3>

                        <div class="privacy-options">
                            <div class="privacy-option">
                                <input type="radio" id="public" name="visibility" value="public" class="privacy-radio"
                                       checked>
                                <label for="public" class="privacy-label">
                                    <div class="privacy-header">
                                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none"
                                             stroke="currentColor" stroke-width="2">
                                            <circle cx="12" cy="12" r="10"></circle>
                                            <path d="M2 12h20"></path>
                                            <path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path>
                                        </svg>
                                        <span class="privacy-title">Public</span>
                                    </div>
                                    <p class="privacy-description">Anyone can view and search for this snippet</p>
                                </label>
                            </div>
                            <div class="privacy-option">
                                <input type="radio" id="private" name="visibility" value="private"
                                       class="privacy-radio">
                                <label for="private" class="privacy-label">
                                    <div class="privacy-header">
                                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none"
                                             stroke="currentColor" stroke-width="2">
                                            <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                                            <circle cx="12" cy="16" r="1"></circle>
                                            <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
                                        </svg>
                                        <span class="privacy-title">Private</span>
                                    </div>
                                    <p class="privacy-description">Only you can view this snippet</p>
                                </label>
                            </div>
                        </div>
                    </div>

                    <!-- Form Actions -->
                    <div class="form-actions">
                        <button type="submit" class="btn">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="lucide lucide-share2-icon lucide-share-2">
                                <circle cx="18" cy="5" r="3"></circle>
                                <circle cx="6" cy="12" r="3"></circle>
                                <circle cx="18" cy="19" r="3"></circle>
                                <line x1="8.59" x2="15.42" y1="13.51" y2="17.49"></line>
                                <line x1="15.41" x2="8.59" y1="6.51" y2="10.49"></line>
                            </svg>
                            Share Snippet
                        </button>
                    </div>
                </form>

            </div>
        </div>
    </main>
</div>
</body>
</html>
