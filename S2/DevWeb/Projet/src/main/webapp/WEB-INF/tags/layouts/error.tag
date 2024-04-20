<%@tag description="layout/error" pageEncoding="UTF-8" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<%@attribute name="message" fragment="true" %>

<layout:base>
    <jsp:attribute name="title">Cards Rush - Erreur</jsp:attribute>
    <jsp:body>
        <div class="hero is-primary is-fullheight-with-navbar">
            <div class="hero-body">
                <div class="container">
                    <div class="columns is-centered">
                        <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                            <div class="box">
                                <h1 class="title has-text-centered">
                                    <jsp:invoke fragment="message"/>
                                </h1>
                                <jsp:doBody/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:base>
