<%@ tag import="uppa.project.database.pojo.User" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<%@tag description="component/navbar" pageEncoding="UTF-8" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

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
            <select name="language-select" id="language-select" required>
                <% if (session.getAttribute("language") != null && session.getAttribute("language").equals("EN")) { %>
                    <option selected value="EN">EN</option>
                    <option value="FR">FR</option>
                <% } else { %>
                    <option value="EN">EN</option>
                    <option selected value="FR">FR</option>
                <% } %>
            </select>
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
                    <span><%= translator.translate("navbar_logout")%></span>
                </a>
            </div>

            <% } else {%>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/register" class="is-fullwidth button is-primary has-text-white"><%= translator.translate("navbar_register")%></a>
            </div>
            <div class="navbar-item">
                <a href="${pageContext.request.contextPath}/login" class="is-fullwidth button is-light">
                    <span class="icon">
                        <i class="fa-solid fa-right-to-bracket"></i>
                    </span>
                    <span><%= translator.translate("navbar_login")%></span>
                </a>
            </div>
            <% } %>
        </div>
    </div>
</nav>
