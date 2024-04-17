<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base>
    <jsp:attribute name="title">CardRush - Mot de passe oublié</jsp:attribute>
    <jsp:body>
        <div class="hero is-light is-fullheight-with-navbar">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                            <div class="box">
                                <h1 class="title has-text-centered">Mot de passe oublié ?</h1>
                                <p class="content"> Veuillez entrer votre email pour obtenir un lien de récupération</p>
                                <form:forgotten-password/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:base>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Forgotten Password</title>--%>
<%--    <meta charset="UTF-8">--%>
<%--&lt;%&ndash;    <link href="${pageContext.request.contextPath}/static/css/forgotten-password.css" rel="stylesheet">&ndash;%&gt;--%>
<%--    <script src="${pageContext.request.contextPath}/static/js/forgotten-password.js" defer></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <%@include file="../components/navbar.jsp"%>--%>
<%--    <main>--%>
<%--        <h1>Mot de passe oublié</h1>--%>
<%--        <p>Entrer votre email pour recevoir un lien de récupération</p>--%>
<%--        <form id="forgottenPasswordForm" action="forgotten-password" method="post">--%>
<%--            <label for="email">Email</label>--%>
<%--            <input type="email" id="email" name="email" required>--%>
<%--            <button type="submit">Send</button>--%>
<%--        </form>--%>
<%--        <%if(request.getParameter("error") != null && request.getParameter("error").equals("1")){%>--%>
<%--        <p>L'adresse mail insérée est incorrecte</p>--%>
<%--        <%} else if (request.getParameter("success") != null) {%>--%>
<%--        <p>Un email vous a été envoyé</p>--%>
<%--        <%}%>--%>
<%--    </main>--%>
<%--</body>--%>
<%--</html>--%>
