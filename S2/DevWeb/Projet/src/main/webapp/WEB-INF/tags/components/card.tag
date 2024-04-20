<%@tag description="component/card" pageEncoding="UTF-8" %>

<%@attribute name="title"%>

<%@attribute name="header_icon" fragment="true" %>
<%@attribute name="image" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<div class="card">
    <div class="card-header">
        <div class="card-header-title">${title}</div>
        <div class="card-header-icon">
            <jsp:invoke fragment="header_icon"/>
        </div>
    </div>
    <div class="card-image">
        <jsp:invoke fragment="image"/>
    </div>
    <div class="card-content">
        <jsp:doBody/>
    </div>
    <div class="card-footer">
        <jsp:invoke fragment="footer"/>
    </div>
</div>