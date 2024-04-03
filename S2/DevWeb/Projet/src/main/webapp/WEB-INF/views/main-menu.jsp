<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<User> connectedUsers = (List<User>) request.getAttribute("connectedUsers"); %>
<!DOCTYPE html>
<html>
<head>
  <title>Cards Rush</title>
</head>
<body>
  <main>
    <jsp:include page="../components/navbar.jsp"/>
    <section id="main">
      <h1 id="Title">Titre du jeu</h1>
      <div class="main-button">
        <button class="modal-toggle" data-target="#newGameModal" >Nouvelle Partie</button>
        <button class="modal-toggle" data-target="#statisticsModal" >Statistiques</button>
        <%@include file="../components/new-game.jsp"%>
        <%@include file="../components/statistics.jsp"%>
      </div>
    </section>

  </main>
</body>
<script defer type="text/javascript"><%@include file="../static/js/modal.js"%></script>
<script defer type="text/javascript"><%@include file="../static/js/new-game.js"%></script>
</html>
