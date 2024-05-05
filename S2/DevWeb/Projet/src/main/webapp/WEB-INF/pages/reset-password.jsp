<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<layout:form title="Récuperation mot de passe">
    <jsp:attribute name="script">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/form/reset-password.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <div class="box">
                        <h1 class="title has-text-centered">Récupération de mot de passe</h1>
                        <form:reset-password/>
                        <hr/>
                        <p class="content has-text-centered">
                            Déjà inscrit ? <a href="${pageContext.request.contextPath}/login">Se connecter</a>
                        </p>
                    </div>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:form>
