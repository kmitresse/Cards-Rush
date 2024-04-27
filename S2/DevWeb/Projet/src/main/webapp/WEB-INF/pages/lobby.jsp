<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>


<layout:base>
    <component:hero>
        <div class="columns">
            <div class="column">
                <component:card title="Menu principal">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/new">Nouvelle partie</a></li>
<%--                        <li><a href="${pageContext.request.contextPath}/join">Rejoindre une partie</a></li>--%>
                    </ul>
                </component:card>
            </div>
            <div class="column">
                <component:card title="Utilisateurs connectés">
                    <component:connected-user-list/>
                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
