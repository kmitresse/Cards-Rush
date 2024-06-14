<%@ tag import="uppa.project.web.translation.Translator" %>
<%@tag description="component/statistics" pageEncoding="UTF-8" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>
<% int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage")); %>
<jsp:useBean id="user" class="uppa.project.database.pojo.User" scope="session"/>
<input type="hidden" id="current-page" value="<%=currentPage%>"/>
<input type="hidden" id="nb-pages" value="<%=(int) Math.ceil((double) user.getNbPlayedGame() / 5)%>"/>
<h4 class="title is-4">${translator.translate("global_statistics")}</h4>
<div class="level">
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate("global_statistics_game")}</p>
            <p class="title">${user.nbPlayedGame}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate("global_statistics_win")}</p>
            <p class="title">${user.nbWin}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate("global_statistics_corrects_clics")}</p>
            <p class="title">${user.rightClickPercentRate}%</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate("global_statistics_rapid_clics")}</p>
            <p class="title">${user.rapidClickPercentRate}%</p>
        </div>
    </div>
</div>

<h4 class="title is-4">${translator.translate("statistics_games_played")} </h4>
<table id="pagination-players" class="table is-fullwidth">
    <thead>
    <tr>
        <th>${translator.translate("statistics_game_date")}</th>
        <th>${translator.translate("statistics_game_score")}</th>
        <th>${translator.translate("statistics_game_winner")}</th>
        <th></th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<nav class="pagination is-rounded" role="navigation" aria-label="pagination">
    <ul class="pagination-list">
        <li><a class="pagination-previous">${translator.translate('pagination_previous')}</a></li>
        <li><a class="pagination-link pagination-current" aria-label="Goto page 1"><%=currentPage%></a></li>
        <li><a class="pagination-next">${translator.translate('pagination_next')}</a></li>
    </ul>
</nav>
