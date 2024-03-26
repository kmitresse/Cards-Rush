<%@ page import="uppa.project.pojo.User" %>
<%@ page import="uppa.project.pojo.Game" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uppa.project.pojo.Player" %><%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 26/03/2024
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
//modale statistque
<% User user = (User) request.getAttribute("user"); %>
<% ArrayList<Game> games = (ArrayList<Game>) request.getAttribute("games"); %>
<div id="statisticsModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h1> New Game</h1>
        <div id="selfSettings">
            <h2>Statistiques globales</h2>
            <table>
                <tr>
                    <th>Nombre de parties jouées:</th>
                    <td><%= user.getNbPlayedGame() %></td>
                </tr>
                <tr>
                    <th>Nombre de parties gagnées:</th>
                    <td><%= user.getNbWin() %>, <%= user.getWinRate()%></td>
                </tr>
                <tr>
                    <th>Nombre de clicks total:</th>
                    <td><%= user.getNbClicks() %></td>
                </tr>
                <tr>
                    <th>Nombre de clicks corrects:</th>
                    <td><%= user.getNbRightClicks() %>, <%= user.getRightClickPercentRate()%></td>
                </tr>
                <tr>
                    <th>Nombre de clicks rapides:</th>
                    <td><%= user.getNbRapidClicks() %>, <%= user.getRapidClickPercentRate()%></td>
                </tr>
            </table>
        </div>
        <div id="game-selection">
            //listes de game dont chacune est un onglet déroulante des joueurs
            <h2>Statistiques par jeu</h2>
            <table>
                <tr>
                    <th>Date de la partie</th>
                    <th>Nombre de joueurs</th>
                    <th>Nombre de manches</th>
                    <th>Nombre de couleurs</th>
                    <th>Nombre de valeurs par couleur</th>
                    <th>Vainqueur</th>
                </tr>
                <%
                    for (Game game : games) {
                %>
                <tr>
                    <td><%= game.getCreatedAt() %></td>
                    <td><%= game.getNbPlayers() %></td>
                    <td><%= game.getNbRounds() %></td>
                    <td><%= game.getNbColors() %></td>
                    <td><%= game.getNbValuesPerColor() %></td>
                    <td><%= game.getPlayers().get(0).getUser().getUsername() %></td>
                </tr>
                <tr class="dropdown" aria-disabled="false">
                    <table>
                        <tr>
                            <th>Username</th>
                            <th>Score</th>
                            <th>Nombre de click</th>
                            <th>Nombre de click corrects</th>
                            <th>Nombre de click rapides</th>
                        </tr>
                        <%
                            for (Player player : game.getPlayers()) {
                        %>
                        <tr>
                            <td><%= player.getUser().getUsername() %></td>
                            <td><%= player.getScore() %></td>
                            <td><%= player.getClickCount() %></td>
                            <td><%= player.getRightClickCount() %>, <%= player.getRatioRightClick() %></td>
                            <td><%= player.getRapidClickCount() %>, <%= player.getRatioRapidClick() %></td>
                        <%
                            }
                        %>
                    </table>
                </tr>
            </table>

        </div>
    </div>
</div>
