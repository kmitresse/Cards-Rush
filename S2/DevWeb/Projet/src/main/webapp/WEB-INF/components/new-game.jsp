<%@ page import="uppa.project.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="uppa.project.pojo.Game" %>
<%@ page import="uppa.project.pojo.Deck" %><%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 19/03/2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<div id="newGameModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h1> New Game</h1>
        <div id="settings">
            <form>
                <label for="timer">Timer</label>
                <input type="number" id="timer" name="timer" min="<%= Game.TIMER_MIN %>" max="<%= Game.TIMER_MAX %>" value="<%= Game.TIMER_MIN %>">
                <label for="nbColors">Nb couleurs</label>
                <input type="range" id="nbColors" name="nbColors" min="<%= Deck.NB_COLORS_MIN %>" max="<%= Deck.NB_COLORS_MAX %>" value="<%= Deck.NB_COLORS_MAX %>">
                <label for="nbValues">Nb valeurs par couleurs</label>
                <input type="range" id="nbValues" name="nbValues" min="<%= Deck.NB_VALUES_PER_COLOR_MIN %>" max="<%= Deck.NB_VALUES_PER_COLOR_MAX %>" value="<%= Deck.NB_VALUES_PER_COLOR_MAX %>">
                <label for="nbRounds">Nombre de tours</label>
                <input type="number" id="nbRounds" name="nbRounds" min="<%= Game.NB_ROUNDS_MIN %>" max="<%= Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX %>" value="Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX">
            </form>
        </div>
        <div id="players-selection">
            <table>
                <tr>
                    <th>Nom d'utilisateur</th>
                    <th>Nombre de partie jouées</th>
                    <th>% Parties Gagnées</th>
                    <th>% Clicks corrects</th>
                    <th>% Clicks rapides</th>
                    <th>Invite</th>
                </tr>
                <% List<User> connectedUsers = (List<User>) request.getAttribute("connectedUsers"); %>
                <% for (User user : connectedUsers) { %>
                    <tr>
                        <td><%= user.getUsername() %></td>
                        <td><%= user.getNbPlayedGame() %></td>
                        <td><%= user.getWinRate() %></td>
                        <td><%= user.getRightClickPercentRate()%></td>
                        <td><%= user.getRapidClickPercentRate()%></td>
                        <td><input type="checkbox" id="<%= user.getUsername()%>-invite" name="<%= user.getUsername()%>-invite"/></td>
                    </tr>
                <% } %>
            </table>
            <button id="startGame">Commencer la partie</button>
        </div>
    </div>
</div>
```
