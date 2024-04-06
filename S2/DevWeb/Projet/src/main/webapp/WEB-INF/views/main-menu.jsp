<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<User> connectedUsers = (List<User>) request.getAttribute("connectedUsers"); %>
<!DOCTYPE html>
<html>
<head>
  <title>Cards Rush</title>
  <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/main-menu.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/new-game.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/modal.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/modal.js" defer></script>
  <script src="${pageContext.request.contextPath}/static/js/new-game.js" defer></script>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%@include file="../components/navbar.jsp"%>
  <main>
    <section id="main">
      <h1 id="title">Cards Rush!</h1>
      <div class="main-button">
        <button class="modal-toggle button" data-target="#newGameModal" >Nouvelle Partie</button>
        <button class="modal-toggle button" data-target="#statisticsModal" >Statistiques</button>
      </div>
    </section>
    <%@include file="../components/new-game.jsp"%>
    <%@include file="../components/statistics.jsp"%>
  </main>
</body>
</html>
