<%@ tag import="uppa.project.database.pojo.Player" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<%@ tag import="java.util.Date" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<%@tag description="component/statistics" pageEncoding="UTF-8" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<jsp:useBean id="user" class="uppa.project.database.pojo.User" scope="session"/>
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
<table class="table is-fullwidth">
    <thead>
    <tr>
        <th>${translator.translate("statistics_game_date")}</th>
        <th>${translator.translate("statistics_game_score")}</th>
        <th>${translator.translate("statistics_game_winner")}</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < user.getPlayedGames().size(); i++) {
        Player player = user.getPlayedGames().get(i);
    %>
        <tr>
            <% Date date = player.getGame().getCreatedAt();
                String language = translator.getLanguage();
                SimpleDateFormat sdfDay;
                SimpleDateFormat sdfHour;
                if (language.equals("EN")) {
                    sdfDay = new SimpleDateFormat("MM/dd/yyyy");
                    sdfHour = new SimpleDateFormat("HH:mm");
                } else {
                    sdfDay = new SimpleDateFormat("dd/MM/yyyy");
                    sdfHour = new SimpleDateFormat("HH:mm");
                }

                String day = sdfDay.format(date);
                String hour = sdfHour.format(date);
            %>
            <td><%=day%>, <%=hour%> </td>
            <td><%= player.getScore() %></td>
            <td><%= player.getGame().getWinner().getUser().getUsername() %></td>
            <td><a href="${pageContext.request.contextPath}/game-statistics?id=<%= player.getGame().getId() %>">${translator.translate("statistics_game_show")}</a></td>
        </tr>
    <% } %>
    </tbody>
</table>
