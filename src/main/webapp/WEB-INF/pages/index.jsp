<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="uppa.project.Global" %>
<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page import="java.text.MessageFormat" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<% Translator translator =(Translator)session.getAttribute("translator");%>

<layout:base title="Accueil">
    <jsp:attribute name="head">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/rewriteUrl.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <p class="title is-2 mb-4 has-text-white">${MessageFormat.format(translator.translate('index_main_sentence'), Global.APP_NAME)}</p>
            <p style="max-width: 30em;" class="mb-6 subtitle has-text-white">
                    ${MessageFormat.format(translator.translate('index_description'), Global.APP_NAME)}
            </p>

            <div class="buttons">
                <a class="button is-light is-primary is-large" href="${pageContext.request.contextPath}/register">${translator.translate('register_title')}</a>
                <a class="button is-primary is-large has-text-white" href="${pageContext.request.contextPath}/login">
                        ${translator.translate('login_title')}
                </a>
            </div>
        </component:hero>
    </jsp:body>
</layout:base>

