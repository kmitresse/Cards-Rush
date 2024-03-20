<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <script defer type="text/javascript"><%@include file="../static/js/login.js" %></script>
    <style><%@include file="../static/css/login.css" %></style>
</head>
<body>

<main>
    <section id="title"> Titre du jeu </section>
    <section id="form">
        <div class="border-left"></div>
        <div class="flex-column login-gap">
            <div>
                <h1>Login</h1>
                <form id="login-form" action="${pageContext.request.contextPath}/login" method="POST">

                    <label id="username-label" for="username">Username:</label>
                    <input type="text" id="username" name="username" required>

                    <label id="password-label" for="password">Password:</label>
                    <input type="password" id="password" name="password" required>

                    <input id="login-submit" type="submit" value="Login">

                </form>
            </div>
            <div>
                <p><a href="${pageContext.request.contextPath}/forgotten-password">Forgotten password?</a></p>
                <hr>
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register</a></p>
            </div>
        </div>
    </section>
</main>

</body>
</html>
