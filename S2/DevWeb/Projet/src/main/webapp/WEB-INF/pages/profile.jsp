<%@ page import="uppa.project.database.pojo.Player" %>
<%@ page import="uppa.project.database.pojo.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib uri = "https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api" prefix = "c" %>--%>



<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base title="Profil">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <component:card title="Profil">
                    <fieldset>
                        <form:profile/>
                    </fieldset>

                </component:card>
            </div>

            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <component:card title="Statistiques">
                    <component:statistics/>
                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>
<script defer type="text/javascript">
    const gameList = document.getElementById('game-list');
    console.log("gameList", gameList)
    ${user.playedGames}.forEach((player) => {
      console.log("player", player)
        gameListTr = document.createElement('tr');
        gameListTdDate = document.createElement('td');
        gameListTdDate.innerHTML = player.game.createdAt;
        gameListTdScore = document.createElement('td');
        gameListTdScore.innerHTML = player.score;
        gameListTdWinner = document.createElement('td');
        gameListTdWinner.innerHTML = player.game.winner;
        gameListTdLink = document.createElement('td');
        gameListTdLink.innerHTML = `<a href="/game/${player.game.id}">Voir</a>`;
        gameListTr.appendChild(gameListTdDate);
        gameListTr.appendChild(gameListTdScore);
        gameListTr.appendChild(gameListTdWinner);
        gameListTr.appendChild(gameListTdLink);
    })
</script>
