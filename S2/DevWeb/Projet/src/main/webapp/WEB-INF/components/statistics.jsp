<%@ page import="uppa.project.pojo.User" %>
<%@ page import="uppa.project.pojo.Game" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uppa.project.pojo.Player" %>
<%@ page import="java.util.Date" %>
<%@ page pageEncoding="UTF-8" %>


<%--TODO: adapter les deux lignes suivante pour ne pas vérifier la valeur nulle--%>
<% User user = (User) session.getAttribute("user") != null ? (User) session.getAttribute("user") : new User("toto", "toto@gmail.com", "totopassword", new Date(), User.Gender.MALE); %>
<% ArrayList<Game> games = (ArrayList<Game>) request.getAttribute("games") != null ? (ArrayList<Game>) request.getAttribute("games") : new ArrayList<>() ; %>
<div id="statisticsModal" class="modal-wrapper" style="display: none">
    <div class="modal">
        <a href="#" title="Close" class="close">&times;</a>
        <div class="modal-header">
            <h2>Statistiques</h2>
        </div>
        <div class="modal-content">
            <div id="self-stats">
                <h2>Statistiques globales</h2>
                <table>
                    <tr>
                        <th>Nombre de parties jouées:</th>
                        <td><%= user.getNbPlayedGame() %></td>
                    </tr>
                    <tr>
                        <th>Nombre de parties gagnées:</th>
                        <td><%= user.getNbWin() %>, <%= user.getWinRate()%>%</td>
                    </tr>
                    <tr>
                        <th>Nombre de clics total:</th>
                        <td><%= user.getNbClicks() %></td>
                    </tr>
                    <tr>
                        <th>Nombre de clics corrects:</th>
                        <td><%= user.getNbRightClicks() %>, <%= user.getRightClickPercentRate()%>%</td>
                    </tr>
                    <tr>
                        <th>Nombre de clics rapides:</th>
                        <td><%= user.getNbRapidClicks() %>, <%= user.getRapidClickPercentRate()%>%</td>
                    </tr>
                </table>
            </div>
            <div id="game-selection">
<%--            listes de game dont chacune est un onglet déroulante des joueurs--%>
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
                                <th>Nombre de clic</th>
                                <th>Nombre de clic corrects</th>
                                <th>Nombre de clic rapides</th>
                            </tr>
                            <%
                                for (Player player : game.getPlayers()) {
                            %>
                            <tr>
                                <td><%= player.getUser().getUsername() %></td>
                                <td><%= player.getScore() %></td>
                                <td><%= player.getClickCount() %></td>
                                <td><%= player.getRightClickCount() %>, <%= player.getRatioRightClick() %>%</td>
                                <td><%= player.getRapidClickCount() %>, <%= player.getRatioRapidClick() %>%</td>
                            </tr>
                            <% } %>
                        </table>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</div>
