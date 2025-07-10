<%--
  Created by IntelliJ IDEA.
  User: bjung
  Date: 7/10/25
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>

<header class="header">
    <div class="container">
        <div class="header-content">
            <div class="logo">
                <a href="/">
                    <h2>CodeShare</h2>
                </a>
            </div>

            <c:if test="${user == null}">
                <div class="header-actions">
                    <a href="auth/login" class="btn btn-outline">Sign In</a>
                    <a href="auth/register" class="btn btn-primary">Sign Up</a>
                </div>
            </c:if>
            <c:if test="${user != null}">
                <div class="header-actions">
                    <span>Hello, ${user.username}</span>
                    <a href="${pageContext.request.contextPath}/snippet/new" class="btn btn-outline">Create</a>
                </div>
            </c:if>
        </div>
    </div>
</header>
