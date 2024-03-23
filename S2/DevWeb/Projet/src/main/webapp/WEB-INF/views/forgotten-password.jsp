<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 20/03/2024
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgotten Password</title>
</head>
<body>
    <main>
        <h1>Forgotten Password</h1>
        <p>Entrer votre email pour recevoir un lien de récupération</p>
        <form id="forgottenPasswordForm" action="forgotten-password" method="post">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
            <button type="submit">Send</button>
        </form>
        <%if(request.getParameter("error") != null && request.getParameter("error").equals("1")){%>
        <p>L'adresse mail insérée est incorrecte</p>
        <%} else if (request.getParameter("success") != null) {%>
        <p>Un email vous a été envoyé</p>
        <%}%>
    </main>
</body>
<script><%@include file="../static/js/forgotten-password.js"%></script>
</html>
