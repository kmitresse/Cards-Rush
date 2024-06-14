<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<layout:base title="${translator.translate('game_statistics_title')}">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-11-tablet is-11-desktop is-11-widescreen">
                <component:card title="${translator.translate('game_statistics_title')}">
                    <component:game-statistics/>
                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
