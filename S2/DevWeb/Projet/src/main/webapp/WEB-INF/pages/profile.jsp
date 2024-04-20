<%@ page import="uppa.project.database.pojo.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<% User user = (User) session.getAttribute("user"); %>

<layout:base title="Profil">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                    <%--                TODO: Profil--%>
            </div>
        </div>
    </component:hero>
</layout:base>