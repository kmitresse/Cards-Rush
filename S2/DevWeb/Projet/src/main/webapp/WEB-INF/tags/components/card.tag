<%@tag description="component/card" pageEncoding="UTF-8" %>
<%@attribute name="card_head" fragment="true" %>
<%@attribute name="card_content" fragment="true" %>

<div class="card">
    <div class="card-head">
        <jsp:invoke fragment="card_head"/>
    </div>
    <div class="card-content">
        <jsp:invoke fragment="card_content"/>
    </div>
</div>