<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:error>
    <jsp:attribute name="message">Token invalide</jsp:attribute>
    <jsp:body>
        <a href="login" class="button is-primary is-fullwidth has-text-white">Retour à la page de connexion</a>
    </jsp:body>
</layout:error>