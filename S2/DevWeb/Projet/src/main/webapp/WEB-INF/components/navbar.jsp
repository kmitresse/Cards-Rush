<%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 19/03/2024
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style><%@include file="/static/css/navbar.css"%></style>
<nav>
    <a href="index"><img src="${pageContext.request.contextPath}/static/img/CardsRushLogo.png" alt="Logo CardsRush"> </a>
    <p>
        username: <!-- Récuperer le nom utilisateur --> <br/>
        best score: <!-- Récuperer le meilleur score -->
    </p>
</nav>
