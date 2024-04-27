<%@ tag import="uppa.project.database.pojo.Player" %>
<%@ tag import="java.util.Calendar" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@tag description="component/statistics" pageEncoding="UTF-8" %>
<jsp:useBean id="game" class="uppa.project.database.pojo.Game" scope="request"/>
<h4 class="title is-6">Information sur la partie</h4>
<div class="level">
    <div class="level-item has-text-centered has-text-on-top">
        <div>
            <% String date = game.getCreatedAt().toLocaleString();
                System.out.println(date);
               String day = date.substring(0,8) + date.substring(10,12);
               String hour = date.substring(14,16) + "h" + date.substring(17,19);
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
            <p class="title">${game.nbRounds}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de couleurs</p>
            <p class="title">${game.nbColors}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de valeurs</p>
            <p class="title">${game.nbValuesPerColor}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Nombre de joueurs</p>
            <p class="title">${game.nbPlayers}</p>
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

    <% for (Player player : game.getPlayers()) { %>
        <tr>
            <td><%= player.getUser().getUsername() %></td>
            <td><%= player.getScore() %></td>
            <td><%= player.getRightClickCount() %>  (<%= player.getRatioRightClick() %>%)</td>
            <td><%= player.getRapidClickCount() %> (<%= player.getRatioRapidClick()%>%)</td>
            <td><% if (player.getUser().getUsername().equals(game.getWinner())){ %> <i class="fa-solid fa-crown" style="color: #FFD43B;"></i> <% } %></td>
        </tr>
    <% } %>
    </tbody>
</table>


<div class="navbar-item">
    <a href="${pageContext.request.contextPath}/profile" class="button is-light is-right">
                    <span class="icon">
                        <i class="fa-solid fa-arrow-left"></i>
                    </span>
        <span>Retour</span>
    </a>
</div>
