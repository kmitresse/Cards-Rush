<%@ tag import="uppa.project.database.pojo.User" %>
<%@tag description="component/navbar" pageEncoding="UTF-8" %>

<nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <% if (session.getAttribute("user") != null) { %>

        <a class="navbar-item" href="${pageContext.request.contextPath}/lobby">
            <img src="${pageContext.request.contextPath}/static/img/CardsRushLogoBlack.svg" height="260" alt="Logo">
        </a>

        <% } else { %>

        <a class="navbar-item" href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/static/img/CardsRushLogoBlack.svg" height="260" alt="Logo">
        </a>

        <% } %>

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
                <a href="${pageContext.request.contextPath}/profile" class="is-fullwidth button is-light">
                    <span class="icon">
                        <i class="fa-solid fa-user"></i>
                    </span>
                    <span><%= user.getUsername() %></span>
                </a>
            </div>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/logout" class="is-fullwidth button is-light is-danger">
                    <span class="icon">
                        <i class="fa-solid fa-door-open"></i>
                    </span>
                    <span>Déconnexion</span>
                </a>
            </div>

            <% } else {%>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/register" class="is-fullwidth button is-primary has-text-white">Inscription</a>
            </div>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/login" class="is-fullwidth button is-light">
                    <span class="icon">
                        <i class="fa-solid fa-right-to-bracket"></i>
                    </span>
                    <span>Connexion</span>
                </a>
            </div>
            <% } %>
        </div>
    </div>
</nav>

<script type="module" defer>
    // Récupération de tous les éléments de classe "navbar-burger"
    const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

    // Ajout d'un enventListener sur chaque élément
    $navbarBurgers.forEach(el => {
        el.addEventListener('click', () => {

            // Récupere la valeur de l'attribut "data-target"
            const target = el.dataset.target;
            const $target = document.getElementById(target);

            // Ajoute ou supprime la classe "is-active" sur les éléments
            el.classList.toggle('is-active');
            $target.classList.toggle('is-active');
        });
    });
</script>
