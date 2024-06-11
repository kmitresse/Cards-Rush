<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<layout:base title="${translator.translate('profile_title')}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/form.css"/>
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/form/profile.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <component:card title="${translator.translate('profile_title')}">
                        <fieldset>
                            <form:profile/>
                        </fieldset>

                    </component:card>
                </div>

                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <component:card title="${translator.translate('statistics_title')}">
                        <component:statistics/>
                    </component:card>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:base>
