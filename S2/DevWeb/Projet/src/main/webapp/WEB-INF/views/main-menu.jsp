<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Main Menu</title>
</head>
<body>
  <main>

    <jsp:include page="../components/navbar.jsp"/>

    <section id="main">
      <h1 id="Title">Titre du jeu</h1>
      <div class="main-button">
        <input type="button" value="New game" >
        <input type="button" value="Hard mode" >
        <input type="button" value="Statistics" >
      </div>
    </section>
  </main>
</body>
</html>
