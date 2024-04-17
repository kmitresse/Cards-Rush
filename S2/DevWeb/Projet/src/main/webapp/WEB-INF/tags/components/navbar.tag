<%@tag description="component/navbar" pageEncoding="UTF-8" %>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/static/img/CardsRushLogoBlack.svg" height="260">
        </a>

        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarMenu">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarMenu" class="navbar-menu">

        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">

                    <%if (session.getAttribute("user") != null) {%>
<%--                    TODO: Profil de l'utilisateur--%>
                        <a href="${pageContext.request.contextPath}/profile" class="button is-primary has-text-white"><strong>Profil</strong></a>
                        <a href="${pageContext.request.contextPath}/logout" class="button is-danger is-light">Déconnexion</a>
                    <% } else {%>
                        <a href="${pageContext.request.contextPath}/register" class="button is-primary has-text-white"><strong>Inscription</strong></a>
                        <a href="${pageContext.request.contextPath}/login" class="button is-light">Connexion</a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</nav>