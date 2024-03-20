<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Dashboard</title>
  <link rel="stylesheet" href="css/dashboard.css"/>
  <link rel="stylesheet" href="css/nav.css"/>
</head>
<body>
  <main>
    <jsp:include page="navBar.jsp"></jsp:include>
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
