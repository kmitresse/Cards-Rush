<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="uppa.project.Global" %>

<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base>
    <jsp:attribute name="title">Accueil</jsp:attribute>
    <jsp:body>
        <section class="hero is-primary is-fullheight-with-navbar">
            <div class="hero-body">
                <div class="container">
                    <p class="title is-2 mb-4 has-text-white">Plongez dans l'excitation de ${Global.APP_NAME}!</p>
                    <p style="max-width: 30em;" class="mb-6 subtitle has-text-white">
                        Défiez votre réactivité et dominez le jeu avec des amis dans cette course effrénée aux cartes !
                        Rejoignez maintenant pour vivre l'adrénaline.
                    </p>

                    <div class="buttons">
                        <a class="button is-light is-primary is-large" href="${pageContext.request.contextPath}/register">S'inscrire</a>
                        <a class="button is-primary is-large has-text-white" href="${pageContext.request.contextPath}/login">
                            Se connecter
                        </a>
                    </div>
                </div>
            </div>
        </section>
    </jsp:body>
</layout:base>