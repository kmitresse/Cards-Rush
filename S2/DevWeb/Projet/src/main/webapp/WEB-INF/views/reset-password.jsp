<%@ page import="uppa.project.dao.jpa.DAO_JPA_RecoveryPasswordToken" %>
<%@ page import="uppa.project.pojo.RecoveryPasswordToken" %>
<%@ page import="uppa.project.dao.DAOException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Récupération du mot de passe</title>
    <meta charset="UTF-8">
<%--      <link href="${pageContext.request.contextPath}/static/css/reset-password.css" rel="stylesheet">--%>
    <script src="${pageContext.request.contextPath}/static/js/reset-password.js" defer></script>
</head>
<body>
    <main>
        <jsp:include page="../components/navbar.jsp"/>
        <h1>Récupération du mot de passe</h1>
        <form id="resetPasswordForm" action="${pageContext.request.contextPath}/reset-password" method="post">
            <label for="newPassword">Nouveau mot de passe</label>
            <input type="password" id="newPassword" name="newPassword" required>
            <label for="confirmPassword">Confirmer le mot de passe</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <% if (request.getParameter("error") != null && request.getParameter("error").equals("matching-password")) {%>
                <p>Les mots de passe ne correspondent pas</p>
            <% } %>
            <input type="hidden" name="token" value="${param.token}">
            <input type="submit" value="Valider">
        </form>
    </main>
</body>
</html>
