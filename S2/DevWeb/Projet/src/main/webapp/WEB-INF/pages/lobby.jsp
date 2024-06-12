<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<layout:base title="${translator.translate('lobby_title')}">
    <jsp:attribute name="head">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/websockets/connected-users-websocket.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns">
                <div class="column">
                    <component:card title="${translator.translate('lobby_title')}">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/new">${translator.translate('lobby_new_game')}</a></li>
                            <li><a href="${pageContext.request.contextPath}/rules">${translator.translate('lobby_rules')}</a></li>
                        </ul>
                    </component:card>
                </div>
                <div class="column">
                    <component:card title="${translator.translate('lobby_title')}">
                        <component:connected-user-list/>
                    </component:card>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:base>
