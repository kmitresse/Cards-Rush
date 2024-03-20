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
        <div class="border-left"></div>
        <div class="flex-column login-gap">
            <div>
                <h1>Login</h1>
                <form id="login-form" data-login-endpoint="api/login" action="dashboard.jsp" method="POST">

                    <label id="username-label" for="username">Username:</label>
                    <input type="text" id="username" name="username" required>

                    <label id="password-label" for="password">Password:</label>
                    <input type="password" id="password" name="password" required>

                    <input id="login-submit" type="submit" value="Login">

                </form>
            </div>
            <div>
                <hr>
                <p>Don't have an account? <a href="register.jsp">Register</a></p>
            </div>
        </div>
    </section>
</main>

</body>
</html>
