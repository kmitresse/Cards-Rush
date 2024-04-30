<%@ tag import="uppa.project.database.pojo.Player" %>
<%@tag description="component/statistics" pageEncoding="UTF-8" %>
<jsp:useBean id="user" class="uppa.project.database.pojo.User" scope="session"/>
<h4 class="title is-4">Statistiques globales</h4>
<div class="level">
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Parties</p>
            <p class="title">${user.nbPlayedGame}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Victoires</p>
            <p class="title">${user.nbWin}</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Clics corrects</p>
            <p class="title">${user.rightClickPercentRate}%</p>
        </div>
    </div>
    <div class="level-item has-text-centered">
        <div>
            <p class="heading">Clics rapides</p>
            <p class="title">${user.rapidClickPercentRate}%</p>
        </div>
    </div>
</div>

<h4 class="title is-4">Parties jouées </h4>
<table class="table is-fullwidth">
    <thead>
    <tr>
        <th>Date</th>
        <th>Score</th>
        <th>Vainqueur</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < user.getPlayedGames().size(); i++) {
        Player player = user.getPlayedGames().get(i);
        System.out.println(player.toString());
    %>
        <tr>
            <td><%= player.getGame().getCreatedAt().toLocaleString() %></td>
            <td><%= player.getScore() %></td>
            <td><%= player.getGame().getWinner().getUser().getUsername() %></td>
            <td><a href="${pageContext.request.contextPath}/game-statistics?id=<%= player.getGame().getId() %>">Voir</a></td>
        </tr>
    <% } %>
    </tbody>
</table>
