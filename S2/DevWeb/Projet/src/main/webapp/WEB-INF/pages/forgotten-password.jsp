<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:form title="Mot de passe oublié">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <div class="box">
                    <h1 class="title has-text-centered">Mot de passe oublié ?</h1>
                    <form:forgotten-password/>
                </div>
            </div>
        </div>
    </component:hero>
</layout:form>