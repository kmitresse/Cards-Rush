<%@tag description="layout/error" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<%@attribute name="message"%>

<layout:base title="Erreur">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <div class="box">
                    <h1 class="title has-text-centered">${message}</h1>
                    <jsp:doBody/>
                </div>
            </div>
        </div>
    </component:hero>
</layout:base>
