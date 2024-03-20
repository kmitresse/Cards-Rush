<%--
  Created by IntelliJ IDEA.
  User: kmitr
  Date: 19/03/2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <main>
        <section id="newGame">
            <h1> New Game</h1>
            <div id="settings">
                <form>
                    <label for="nbRounds">Nb Rounds</label>
                    <input type="number" id="nbRounds" name="nbRounds" min="1" max="50" value="10">
                    <label for="timer">Timer</label>
                    <input type="number" id="timer" name="timer" min="1" max="10" value="5">
                    <label for="nbColors">Nb colors</label>
                    <input type="range" id="nbColors" name="nbColors" min="1" max="4" value="4">
                    <label for="nbValues">Nb values per colors</label>
                    <input type="range" id="nbValues" name="nbValues" min="5" max="13" value="13">
                </form>
            </div>
            <div id="players-selection">
                <table>
                    <tr>
                        <th>User</th>
                        <th>Game played</th>
                        <th>Game won</th>
                        <th>Invite</th>
                    </tr>
                    <%-- Récuperer les joueurs connecter et les lister sous forme de tableau--%>
                </table>
            </div>
        </section>
    </main>
</body>
</html>
