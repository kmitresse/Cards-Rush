<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base title="Profil">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-9-tablet is-9-desktop is-9-widescreen">
                <component:card title="Statistiques de la partie">
                    <component:game-statistics/>
                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
