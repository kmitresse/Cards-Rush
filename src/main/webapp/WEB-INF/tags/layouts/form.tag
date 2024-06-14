<%@tag description="layout/base" pageEncoding="UTF-8" %>

<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>

<%@attribute name="title"%>
<%@attribute name="script" fragment="true" %>


<layout:base title="${title}">
    <jsp:attribute name="head">
        <jsp:invoke fragment="script"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</layout:base>
