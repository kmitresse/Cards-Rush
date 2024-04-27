<%@ page import="uppa.project.database.pojo.Player" %>
<%@ page import="uppa.project.database.pojo.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib uri = "https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api" prefix = "c" %>--%>



<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base title="Profil">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/form.css"/>
    </jsp:attribute>
    <jsp:body>
        <component:hero>
            <div class="columns is-centered">
                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <component:card title="Profil">
                        <fieldset>
                            <form:profile/>
                        </fieldset>

                    </component:card>
                </div>

                <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <component:card title="Statistiques">
                        <component:statistics/>
                    </component:card>
                </div>
            </div>
        </component:hero>
    </jsp:body>
</layout:base>
