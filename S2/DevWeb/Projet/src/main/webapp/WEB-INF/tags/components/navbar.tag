<%@ tag import="uppa.project.database.pojo.User" %>
<%@tag description="component/navbar" pageEncoding="UTF-8" %>

<nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
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
            <%if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
            %>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/profile" class="is-fullwidth button is-light"><%= user.getUsername() %></a>
            </div>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/logout" class="is-fullwidth button is-light is-danger">Déconnexion</a>
            </div>

            <% } else {%>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/register" class="is-fullwidth button is-primary has-text-white">Inscription</a>
            </div>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/login" class="is-fullwidth button is-light">Connexion</a>
            </div>
            <% } %>
        </div>
    </div>
</nav>

<script type="module" defer>
    // Get all "navbar-burger" elements
    const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

    // Add a click event on each of them
    $navbarBurgers.forEach(el => {
        el.addEventListener('click', () => {

            // Get the target from the "data-target" attribute
            const target = el.dataset.target;
            const $target = document.getElementById(target);

            // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
            el.classList.toggle('is-active');
            $target.classList.toggle('is-active');

        });
    });
</script>