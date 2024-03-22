<%@ page import="uppa.project.dao.jpa.DAO_JPA_RecoveryPasswordToken" %>
<%@ page import="uppa.project.pojo.RecoveryPasswordToken" %>
<%@ page import="uppa.project.dao.DAOException" %>
<%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 22/03/2024
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recovery password</title>
</head>
<body>
    <main>
        <%
            DAO_JPA_RecoveryPasswordToken dao = null;
            RecoveryPasswordToken[] token;
            try {
                dao = new DAO_JPA_RecoveryPasswordToken();
                token = dao.findByField("token",request.getParameter("token"));
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            if (token.length == 0 || token[0] == null || token[0].getExpirationDate()== null) {%>
        <p> Lien invalide </p>
        <%
            } else if (token[0].getExpirationDate().compareTo(new java.util.Date()) >0){
        %>
        <p> Lien expiré </p>
        <%
            } else {
        %>
        <jsp:include page="../components/navbar.jsp"/>
        <h1>Récupération du mot de passe</h1>
        <form id="resetPasswordForm" action="reset-password" method="post">
            <label for="newPassword">Nouveau mot de passe</label>
            <input type="password" id="newPassword" name="newPassword" required>
            <label for="confirmPassword">Confirmer le mot de passe</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) {%>
            <p>Les mots de passe ne correspondent pas</p>
            <% } %>
            <input type="hidden" name="token" value="${param.token}">
            <input type="submit" value="Valider">
        </form>
        <%
            }
        %>
    </main>
</body>
</html>
