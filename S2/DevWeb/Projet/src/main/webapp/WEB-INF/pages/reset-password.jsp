<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<layout:base>
    <jsp:attribute name="title">Cards Rush - Récuperation mot de passe</jsp:attribute>
    <jsp:body>
        <div class="hero is-light is-fullheight-with-navbar">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                            <div class="box">
                                <h1 class="title has-text-centered">Récupération de mot de passe</h1>
                                <form:reset-password/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:base>
