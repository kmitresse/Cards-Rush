<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base>
    <jsp:attribute name="title">Menu principal</jsp:attribute>
    <jsp:body>
        <div class="hero is-primary is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                            <component:card>
                                <jsp:attribute name="card_head">

                                </jsp:attribute>
                                <jsp:attribute name="card_content">
                                    <form:newGame/>
                                </jsp:attribute>
                            </component:card>
                        </div>
                        <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                            <component:connectedUserList/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:base>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Cards Rush</title>--%>
<%--    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap">--%>
<%--    <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">--%>
<%--    <link href="${pageContext.request.contextPath}/static/css/main-menu.css" rel="stylesheet">--%>
<%--    <link href="${pageContext.request.contextPath}/static/css/new-game.css" rel="stylesheet">--%>
<%--    <link href="${pageContext.request.contextPath}/static/css/modal.css" rel="stylesheet">--%>
<%--    <script src="${pageContext.request.contextPath}/static/js/modal.js" defer></script>--%>
<%--    <script src="${pageContext.request.contextPath}/static/js/new-game.js" defer></script>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--    <script defer src="${pageContext.request.contextPath}/static/js/main-menu.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<%@include file="../components/navbar.jsp" %>--%>
<%--<main>--%>
<%--    <section id="main">--%>
<%--        <h1 id="title">Cards Rush!</h1>--%>
<%--        <div class="main-button">--%>
<%--            <button class="modal-toggle button" data-target="#newGameModal">Nouvelle Partie</button>--%>
<%--            <button class="modal-toggle button" data-target="#statisticsModal">Statistiques</button>--%>
<%--        </div>--%>
<%--    </section>--%>
<%--    <%@include file="../components/new-game.jsp" %>--%>
<%--    <%@include file="../components/statistics.jsp" %>--%>
<%--</main>--%>
<%--</body>--%>
<%--</html>--%>
