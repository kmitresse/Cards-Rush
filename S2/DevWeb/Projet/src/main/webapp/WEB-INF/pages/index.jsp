<%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 03/04/2024
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Card Rush</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap">
    <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
    <meta charset="utf-8">
</head>
<body>
<%@include file="../components/navbar.jsp" %>
<div class="content">
    <h1>Plongez dans l'excitation de CardRush!</h1>
    <p>Défiez votre réactivité et dominez le jeu avec des amis dans cette course effrénée aux cartes ! Rejoignez maintenant pour vivre l'adrénaline.</p>
    <div class="buttons">
        <a class="button" href="${pageContext.request.contextPath}/register">S'inscrire</a>
        <a class="button" href="${pageContext.request.contextPath}/login">Se connecter</a>
    </div>
</div>
</body>
</html>
