<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:form>
    <jsp:attribute name="script">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/form/new-game.js"></script>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <div class="box">
                        <h1 class="title has-text-centered">Nouvelle partie</h1>
                        <form:new-game>
                            <jsp:attribute name="back_button">
                                <div class="column">
                                    <a class="button is-fullwidth" href="${pageContext.request.contextPath}/lobby">Revenir au menu</a>
                                </div>
                            </jsp:attribute>
                        </form:new-game>
                    </div>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:form>
