<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<layout:form title="${translator.translate('forgotten_title')}">
    <jsp:attribute name="script">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/form/forgotten-password.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <div class="box">
                        <h1 class="title has-text-centered">${translator.translate('forgotten_title')}</h1>
                        <form:forgotten-password/>
                    </div>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:form>
