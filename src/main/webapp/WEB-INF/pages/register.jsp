<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<layout:form title='<%= translator.translate("register_title") %>'>
    <jsp:attribute name="script">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/form/register.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <div class="box">
                        <h1 class="title has-text-centered">${translator.translate("register_title")}</h1>
                        <form:register/>
                        <hr/>
                        <p class="content has-text-centered">
                                ${translator.translate("register_already_registered")} <a href="${pageContext.request.contextPath}/login">${translator.translate("register_login")}</a>
                        </p>
                    </div>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:form>
