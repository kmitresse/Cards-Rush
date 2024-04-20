<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>

<layout:base title="Profil">
    <component:hero>
        <div class="columns is-centered">
            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <component:card title="Profil">
                    <fieldset disabled>
                        <jsp:useBean id="user" class="uppa.project.database.pojo.User" scope="session"/>
                        <div class="field">
                            <label class="label">Nom d'utilisateur</label>
                            <input class="input" type="text" value="${user.username}">
                        </div>
                        <div class="field">
                            <label class="label">Email</label>
                            <input class="input" type="text" value="${user.email}">
                        </div>
                        <div class="field">
                            <label class="label">Mot de passe</label>
                            <a href="/profile/password">Changer le mot de passe</a>
                        </div>
                        <div class="field">
                            <label class="label">Date de naissance</label>
                            <input class="input" type="text" value="${user.birth.toLocaleString()}">
                        </div>
                        <div class="field">
                            <label class="label">Genre</label>
                            <input class="input" type="text" value="${user.gender}">
                        </div>
                    </fieldset>

                    <div class="buttons">
                        <button class="button is-primary is-outlined">Modifier</button>
                        <button class="button is-primary has-text-white">Supprimer le compte</button>
                    </div>
                </component:card>
            </div>

            <div class="column is-5-tablet is-5-desktop is-5-widescreen">
                <component:card title="Statistiques">
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
                                <p class="title">${user.nbRightClicks}</p>
                            </div>
                        </div>
                        <div class="level-item has-text-centered">
                            <div>
                                <p class="heading">Clics rapides</p>
                                <p class="title">${user.nbRapidClicks}</p>
                            </div>
                        </div>
                    </div>

<%--                    TODO: Tableau des 10 dernières parties--%>

                    <h4 class="title is-4">10 dernières parties</h4>
                    <table class="table is-fullwidth">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Victoire</th>
                                <th>Score</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="game" items="${user.playedGames}">
                                <tr>
                                    <td>${game.createdAt}</td>
                                    <td>${game.winner}</td>
                                    <td>${game.score}</td>
                                    <td><a href="/game/${game.id}">Voir</a></td>
                                </tr>
                            </c:forEach>
                    </table>


                </component:card>
            </div>
        </div>
    </component:hero>
</layout:base>