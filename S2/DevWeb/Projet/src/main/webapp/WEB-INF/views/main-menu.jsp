<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<User> connectedUsers = (List<User>) request.getAttribute("connectedUsers"); %>
<!DOCTYPE html>
<html>
<head>
  <title>Cards Rush</title>
<%--  <link href="${pageContext.request.contextPath}/static/css/main-menu.css" rel="stylesheet">--%>
  <script src="${pageContext.request.contextPath}/static/js/modal.js" defer></script>
  <script src="${pageContext.request.contextPath}/static/js/new-game.js" defer></script>
  <meta charset="utf-8">
</head>
<body>
  <main>
    <jsp:include page="../components/navbar.jsp"/>
    <section id="main">
      <h1 id="Title">Titre du jeu</h1>
      <div class="main-button">
        <button class="modal-toggle" data-target="#newGameModal" >Nouvelle Partie</button>
        <button class="modal-toggle" data-target="#statisticsModal" >Statistiques</button>
      </div>
    </section>
  </main>
  <%@include file="../components/new-game.jsp"%>
  <%@include file="../components/statistics.jsp"%>
</body>
</html>
