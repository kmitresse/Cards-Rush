<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base>
    <jsp:attribute name="title">Cards Rush - Erreur</jsp:attribute>
    <jsp:body>
        <div class="hero is-primary is-fullheight-with-navbar">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                            <div class="box">
                                <h1 class="title has-text-centered">Token expiré</h1>
                                <a href="login" class="button is-primary is-fullwidth has-text-white">Retour à la page de connexion</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:base>
