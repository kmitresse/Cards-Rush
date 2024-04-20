<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base title="Menu principal">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <component:card title="Nouvelle partie">
                    <form:new-game/>
                </component:card>
            </div>
            <div class="column is-6-tablet is-6-desktop is-6-widescreen">
                <component:card title="Utilisateurs connectés">
                    <component:connected-user-list/>
                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
