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
    <%  if (currentPage.equals("register") || currentPage.equals("forgotten-password") || currentPage.equals("reset-password")){ %>
    <a class="button" href="${pageContext.request.contextPath}/login">Se connecter</a>
    <% }
        if (currentPage.equals("login") || currentPage.equals("forgotten-password") || currentPage.equals("reset-password")) { %>
     <a class="button" href="${pageContext.request.contextPath}/register">S'inscrire</a>
    <% } else if (currentPage.equals("main-menu")){ %>
        <div class="content">
            <% User user = (User) request.getAttribute("user"); %>
            <p class="user">
                <%= user != null ? user.getUsername() : "anonyme"%> <br/>
                <%= user != null ? user.getNbWin() : "0" %> victoires
            </p>
            <a class="button" href="${pageContext.request.contextPath}/logout">Se déconnecter</a>
        </div>
    <% } %>
</nav>
