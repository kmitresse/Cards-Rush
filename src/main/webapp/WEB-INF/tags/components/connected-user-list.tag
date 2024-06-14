<jsp:useBean id="user" scope="session" type="uppa.project.database.pojo.User"/>
<%@tag description="component/connected-user-list" pageEncoding="UTF-8" %>

<%@tag import="com.google.gson.Gson" %>
<%@ tag import="uppa.project.web.translation.Translator" %>

<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>
<input type="hidden" id="user_id" value="${user.getId()}">
<table id="connected-user-list" class="table is-fullwidth">
    <thead>
    <tr>
        <th>${translator.translate('lobby_connected_users_username')}</th>
        <th>${translator.translate('lobby_connected_users_played_games')}</th>
        <th>${translator.translate('lobby_connected_users_wins')}</th>
        <th>${translator.translate('lobby_connected_users_average_score')}</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>

