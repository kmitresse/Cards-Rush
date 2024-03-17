<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <script defer type="text/javascript" src="js/login.js"></script>
    <link rel="stylesheet" href="css/login.css"/>
</head>
<body>

<main>
    <section id="title"> Titre du jeu </section>
    <section id="form">
    <h1>Login</h1>
    <form id="loginForm" data-login-endpoint="api/login" action="dashboard.jsp" method="POST">
        <div class="field">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="field">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <input class="submit" type="submit" value="Login">

        <p>Don't have an account? <a href="register.jsp">Register</a></p>
    </form>

    </section>
</main>

</body>
</html>
