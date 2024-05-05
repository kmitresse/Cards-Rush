<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:form title="Inscription">
    <jsp:attribute name="script">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/form/register.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <div class="box">
                        <h1 class="title has-text-centered">S'inscrire</h1>
                        <form:register/>
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
