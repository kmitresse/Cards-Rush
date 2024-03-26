<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Cards Rush</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css">
</head>
<body>
  <main>
    <jsp:include page="../components/navbar.jsp"/>
    <section id="main">
      <h1 id="Title">Titre du jeu</h1>
      <div class="main-button">
        //create a modal button for new game:
        <button data-toggle="modal" data-target="#newGameModal" >New Game</button>
          <%@include file="../components/new-game.jsp"%>
        </div>;
        <input type="button" value="Statistics" >
      </div>
    </section>
  </main>
</body>
</html>
