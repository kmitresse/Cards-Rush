<%@ tag import="uppa.project.database.pojo.Player" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@ tag import="java.util.ArrayList" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<%@ tag import="java.util.Date" %>
<%@tag description="component/statistics" pageEncoding="UTF-8" %>

<%
    Game game = (Game) request.getAttribute("game");
    ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("players");
%>

<h4 class="title is-6">Information sur la partie</h4>
<div class="level">
    <div class="level-item has-text-centered has-text-on-top">
        <div>
            <% Date date = game.getCreatedAt();

                SimpleDateFormat sdfDay = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");

               String day = sdfDay.format(date);
               String hour = sdfHour.format(date);
            %>
            <p class="heading">Date de jeu</p>
            <p class="title"><%= day %></p>
            <p class="title"><%= hour %></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Difficulté</p>
            <% if (game.getDifficulty().equals(Game.Difficulty.EASY)){%>
                <p class="title">Facile</p>
            <% } else {%>
                <p class="title">Difficile</p>
            <% } %>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de rounds</p>
            <p class="title"><%= game.getNbRounds() %></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de couleurs</p>
            <p class="title"><%= game.getNbColors()%></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de valeurs</p>
            <p class="title"><%= game.getNbValuesPerColor()%></p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de joueurs</p>
            <p class="title"><%= game.getNbPlayers()%></p>
        </div>
    </div>
</div>

<h4 class="title is-4">Joueurs </h4>
<table class="table is-fullwidth">
    <thead>
    <tr>
        <th>Nom d'utilisateur</th>
        <th>Score</th>
        <th>Clics corrects</th>
        <th>Clics rapides</th>
        <th>Victoire</th>
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
        <span>Retour</span>
    </a>
    <%}%>
</div>
