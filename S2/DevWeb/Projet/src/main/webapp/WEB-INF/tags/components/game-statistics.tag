<%@ tag import="uppa.project.web.translation.Translator" %>
<%@ tag import="uppa.project.database.pojo.Player" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@ tag import="java.util.ArrayList" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<%@ tag import="java.util.Date" %>
<%@tag description="component/statistics" pageEncoding="UTF-8" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<%
    Game game = (Game) request.getAttribute("game");
    ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
%>

<h4 class="title is-6">${translator.translate('game_information_title')}</h4>
<div class="level">
    <div class="level-item has-text-centered has-text-on-top">
        <div>
            <% Date date = game.getCreatedAt();

                SimpleDateFormat sdfDay = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");

               String day = sdfDay.format(date);
               String hour = sdfHour.format(date);
            %>
            <p class="heading">${translator.translate('game_statistics_date')}</p>
            <p class="title"><%= day %></p>
            <p class="title"><%= hour %></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate('game_information_difficulty')}</p>
            <% if (game.getDifficulty().equals(Game.Difficulty.EASY)){%>
                <p class="title">${translator.translate('difficulty_easy')}</p>
            <% } else {%>
                <p class="title">${translator.translate('difficulty_hard')}</p>
            <% } %>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate('game_information_rounds_number')}</p>
            <p class="title"><%= game.getNbRounds() %></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate('game_information_deck_color_number')}</p>
            <p class="title"><%= game.getNbColors()%></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate('game_information_deck_value_number')}</p>
            <p class="title"><%= game.getNbValuesPerColor()%></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">${translator.translate('game_informations_player_number')}</p>
            <p class="title"><%= game.getNbPlayers()%></p>
        </div>
    </div>
</div>

<h4 class="title is-4">${translator.translate('game_room_player')}</h4>
<table class="table is-fullwidth">
    <thead>
    <tr>
        <th>${translator.translate('user_username')}</th>
        <th>${translator.translate('game_statistics_score')}</th>
        <th>${translator.translate('game_statistics_correct_clicks')}</th>
        <th>${translator.translate('game_statistics_rapid_clicks')}</th>
        <th>${translator.translate('game_statistics_win')}</th>
    </tr>
    </thead>
    <tbody id="game-list">
    <%
        for (Player player : players) { %>
        <tr>
            <td><%= player.getUser().getUsername() %></td>
            <td><%= player.getScore() %></td>
            <td><%= player.getRightClickCount() %>  (<%= player.getRatioRightClick() %>%)</td>
            <td><%= player.getRapidClickCount() %> (<%= player.getRatioRapidClick()%>%)</td>
            <td>
                <% if (player.getUser().getUsername().equals(game.getWinner().getUser().getUsername())){ %>
                    <i class="fa-solid fa-crown" style="color: #FFD43B;"></i>
                <% } %></td>
        </tr>
    <% } %>
    </tbody>
</table>


<div class="navbar-item">
    <% if (request.getParameter("endGame") != null){ %>
        <a href="${pageContext.request.contextPath}/lobby" class="button is-light is-right">
            <span class="icon">
                <i class="fa-solid fa-arrow-left"></i>
            </span>
            <span>Retour</span>
        </a>
    <% } else {%>
        <a href="${pageContext.request.contextPath}/profile" class="button is-light is-right">
            <span class="icon">
                <i class="fa-solid fa-arrow-left"></i>
            </span>
            <span>${translator.translate('back')}</span>
        </a>
    <%}%>
</div>
