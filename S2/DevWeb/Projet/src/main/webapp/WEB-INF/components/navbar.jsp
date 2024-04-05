<%@ page import="uppa.project.pojo.User" %>
<%@ page pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 19/03/2024
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<% String currentPage =(String) request.getAttribute("current"); %>
<style><%@include file="/static/css/navbar.css"%></style>
<nav>
    <a href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/static/img/CardsRushLogo.png" alt="Logo CardsRush"> </a>
    <%  if (currentPage == "register" || currentPage == "forgotten-password" || currentPage == "reset-password"){ %>
    <button><a href="${pageContext.request.contextPath}/login">Se connecter</a></button>
    <% }
        if (currentPage == "login" || currentPage == "forgotten-password" || currentPage == "reset-password") { %>
    <button> <a href="${pageContext.request.contextPath}/register">S'inscrire</a></button>
    <% } else if (currentPage == "main-menu"){ %>
        <% User user = (User) request.getAttribute("user"); %>
        <p>
            pseudo: <%= user != null ? user.getUsername() : "anonyme"%> <br/>
            nombre de victoires: <%= user != null ? user.getNbWin() : "0" %>
        </p>
        <button><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></button>
    <% } %>
</nav>
