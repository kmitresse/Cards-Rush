<%@tag description="component/hero" pageEncoding="UTF-8" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<div class="hero is-primary is-fullheight-with-navbar">
    <div class="hero-body">
        <div class="container">
            <jsp:doBody/>
        </div>
    </div>
    <component:footer/>
</div>
