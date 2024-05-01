<%@ page import="uppa.project.database.pojo.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>


<layout:base>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/card.css">
    </jsp:attribute>
    <jsp:body>

        <component:hero>

            <div class="columns" id="gameWaiting">
                <div class="column">
                    <component:card title="Liste des joueurs dans la partie">
                    <jsp:attribute name="footer">
                        <a data-target="#user-list-modal" class="card-footer-item modal-trigger">Ajouter</a>
                        <a id="start-game-button" class="is-primary card-footer-item">Démarrer</a>
                    </jsp:attribute>
                        <jsp:body>
                            <table id="playerList" class="table is-fullwidth">
                                <thead>
                                <tr>
                                    <td>Joueur</td>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </jsp:body>
                    </component:card>
                </div>
                <div class="column">
                    <component:card title="Partie n°${pageContext.request.getParameter('id')}">
                        <jsp:useBean id="game" scope="request" type="uppa.project.database.pojo.Game"/>

                        <p><strong>Créé le:</strong> ${game.createdAt.toLocaleString()}</p>
                        <p><strong>Difficulté:</strong> ${game.difficulty}</p>
                        <p><strong>Nombre de tours:</strong> ${game.nbRounds}</p>
                        <p><strong>Nombre de couleurs:</strong> ${game.nbColors}</p>
                        <p><strong>Valeurs par couleur:</strong> ${game.nbValuesPerColor}</p>
                    </component:card>
                </div>
            </div>
            <div id="gameStarted" style="display:none;">
                <div class="columns" id="otherCards"></div>
                <div class="columns">
                    <div class="column is-flex is-flex-direction-column is-align-items-center" id="deck"></div>
                    <div class="column is-one-quarter is-justify-content-center" id="choice"
                         style="position: absolute; right: 0; z-index: 9999">
                        <div class="buttons is-flex-direction-column">
                            <p id="round" class="title has-text-white"></p>
                            <p id="timer" class="subtitle has-text-white"></p>
                            <button class="button is-fullwidth" data-value="COLOR_VALUE">Même couleur et valeur</button>
                            <button class="button is-fullwidth" data-value="COLOR">Même couleur</button>
                            <button class="button is-fullwidth" data-value="VALUE">Même valeur</button>
                            <button class="button is-fullwidth" data-value="NONE">Aucun</button>
                        </div>
                    </div>
                </div>
                <div class="columns is-centered" id="myCard">
                </div>
            </div>
        </component:hero>

        <!-- Liste des utilisateurs dans le lobby -->
        <div id="user-list-modal" class="modal">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title">Liste des utilisateurs connectés</p>
                    <button class="delete" aria-label="close"></button>
                </header>
                <section class="modal-card-body">
                    <table class="table is-fullwidth">
                        <thead>
                        <tr>
                            <th>Utilisateur</th>
                            <th>Nombre de parties jouées</th>
                            <th>Nombre de victoires</th>
                            <th>Clics corrects</th>
                            <th>Clics rapides</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </section>
                <footer class="modal-card-foot">
                    <button class="button is-success">Fermer</button>
                </footer>
            </div>
        </div>

        <jsp:useBean id="user" scope="session" type="uppa.project.database.pojo.User"/>
        <script defer type="module">
            // Modal

            // Modal
            document.querySelectorAll('.modal-trigger').forEach(($el) => {
                $el.addEventListener('click', () => {
                    const target = $el.dataset.target;
                    const $target = document.querySelector(target);
                    $target.classList.add('is-active');
                });
            });

            const closeModal = ($el) => $el.classList.remove('is-active');
            const closeAllModals = () => (document.querySelectorAll('.modal') || []).forEach(($modal) => closeModal($modal));

            // Add a click event on various child elements to close the parent modal
            (document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {
                const $target = $close.closest('.modal');
                $close.addEventListener('click', () => closeModal($target));
            });

            // Add a keyboard event to close all modals
            document.addEventListener('keydown', (event) => {
                if (event.key === "Escape") closeAllModals();
            });
        </script>

        <script defer type="module">
            import WebsocketToolkit from "${pageContext.request.contextPath}/static/js/WebsocketToolkit.js";

            let users = [];

            function updateUsers() {
                const $tbody = document.querySelector('#user-list-modal tbody');
                $tbody.innerHTML = '';
                users.forEach(user => {
                    const $tr = document.createElement('tr');
                    const $tdUsername = document.createElement('td');
                    const $tdNbPlayedGame = document.createElement('td');
                    const $tdNbWins = document.createElement('td');
                    const $tdRightClick = document.createElement('td');
                    const $tdRapidClick = document.createElement('td');
                    const $tdAction = document.createElement('td');
                    const $button = document.createElement('button');

                    $button.classList.add('button', 'is-success');
                    $button.textContent = 'Inviter';
                    $button.addEventListener('click', () => {
                        const data = {
                            from: {
                                id: ${user.id},
                                username: "${user.username}"
                            },
                            to: {...user},
                            game_id: ${game.id}
                        };
                        const message = {
                            type: "invite",
                            data: JSON.stringify(data)
                        };

                        ws.ws.send(JSON.stringify(message));
                    })


                    $tdUsername.textContent = user.username;
                    $tdNbPlayedGame.textContent = user.nbPlayedGames;
                    $tdNbWins.textContent = user.nbWin;
                    $tdRightClick.textContent = user.rigthClickPercentRate + "%";
                    $tdRapidClick.textContent = user.rapidClickPercentRate + "%";
                    $tdAction.appendChild($button);

                    $tr.appendChild($tdUsername);
                    $tr.appendChild($tdNbPlayedGame);
                    $tr.appendChild($tdNbWins);
                    $tr.appendChild($tdRightClick);
                    $tr.appendChild($tdRapidClick);
                    $tr.appendChild($tdAction);

                    $tbody.appendChild($tr);
                });
            }

            // Websocket for users in the lobby
            const url = new URL(window.location.href);
            url.pathname = "${pageContext.request.contextPath}/ws/users/0";
            url.protocol = "ws:"
            url.searchParams.delete("id")

            const ws = new WebsocketToolkit(url);
            ws.onOpen(_ => console.log("Connected to the server"));
            ws.onMessage("init", (data) => {
                users = data;
                updateUsers();
            });
            ws.onMessage("addUser", (data) => {
                users.push(data);
                updateUsers();
            });
            ws.onMessage("removeUser", (data) => {
                users = users.filter(user => user.id !== data.id);
                updateUsers();
            });
            ws.onMessage("invite", (data) => {
                const {from, to, game} = data;
                console.log("User " + from.username + " invited " + to.username + " to play " + game.name);
            })
            ws.onError((error) => console.error(error));
            ws.onClose(() => console.log("Disconnected from the server"));
        </script>

        <script type="module" defer>
            import WebsocketToolkit from "${pageContext.request.contextPath}/static/js/WebsocketToolkit.js";
            import PlayerHand from "${pageContext.request.contextPath}/static/js/PlayerHand.js"
            import Card from "${pageContext.request.contextPath}/static/js/Card.js"

            const choice = document.querySelector('#choice');
            let havePlayed = false;

            const timer = ${game.timer};
            let remainingTime = timer;
            let timerInterval;

            // Display timer
            document.querySelector('#timer').innerText = "Temps restant: " + remainingTime + "s";

            const interval = () => {
                remainingTime--;

                document.querySelector('#timer').innerText = "Temps restant: " + remainingTime + "s";

                if (remainingTime <= 0) clearInterval(timerInterval);
            }

            choice.querySelectorAll('button').forEach(button => {
                button.addEventListener('click', () => {
                    const message = {
                        type: "click",
                        data: button.dataset.value
                    }
                    wsgame.ws.send(JSON.stringify(message));

                    havePlayed = true;

                    // Disable buttons
                    choice.querySelectorAll('button').forEach(button => button.disabled = true);
                });
            });

            const url = new URL(window.location.href);
            url.pathname = "${pageContext.request.contextPath}/ws/game/${game.id}";
            url.protocol = "ws:";
            url.searchParams.delete("id");

            const wsgame = new WebsocketToolkit(url);
            wsgame.onOpen(() => {
                console.log("Connected to the server (GameWS)")

                const message = {
                    type: "connection",
                    data: JSON.stringify({
                        id: ${user.id},
                        username: "${user.username}"
                    })
                }

                wsgame.ws.send(JSON.stringify(message))
            });
            wsgame.onMessage("updatePlayerList", (data) => {
                players = data;
                updatePlayerList();
            });
            wsgame.onMessage("start", (game) => {
                currentGame = game;

                document.querySelector('#gameWaiting').style.display = 'none';
                document.querySelector('#gameStarted').style.display = 'block';

                timerInterval = setInterval(interval, 1000);

                const deck = document.querySelector('#deck'); // Column
                const myCard = document.querySelector('#myCard'); // Column
                const otherCards = document.querySelector('#otherCards'); // Columns
                const round = document.querySelector('#round');

                // Reset content
                deck.innerHTML = "";
                myCard.innerHTML = "";
                otherCards.innerHTML = "";
                round.innerText = "";

                // Show current round
                round.innerText = "Manche " + (currentGame.currentRound + 1)

                // Show other player cards
                game.players
                    .filter(p => p.user.id !== ${user.id})
                    .forEach(p => {
                        const playerHand = new PlayerHand(p);
                        otherCards.innerHTML += playerHand.render({
                            textPosition: PlayerHand.TextPosition.TOP,
                            className: "column"
                        });
                    });

                // Show my card
                const me = game.players.find(p => p.user.id === ${user.id});

                const playerHand = new PlayerHand(me);
                myCard.innerHTML += playerHand.render({
                    textPosition: PlayerHand.TextPosition.BOTTOM,
                    className: "column"
                });

                // Show deck
                const deckCard = new Card(game.currentCard.color, game.currentCard.value);
                deck.innerHTML = deckCard.render();
            })
            wsgame.onMessage("updatePlayer", (p) => {
                document.querySelector(".player-" + p.user.id + " .card-play").style.boxShadow = "inset 0px 0px 30px 10px orange";
            })
            wsgame.onMessage("timerEnd", (game) => {
                if (!havePlayed) {
                    const message = {
                        type: "click",
                        data: "TIMER_END"
                    }
                    wsgame.ws.send(JSON.stringify(message));
                }
            })

            wsgame.onMessage("nextRound", (game) => {
                currentGame = game;

                document.querySelector('#gameWaiting').style.display = 'none';
                document.querySelector('#gameStarted').style.display = 'block';

                const deck = document.querySelector('#deck'); // Column
                const choice = document.querySelector('#choice');
                const myCard = document.querySelector('#myCard'); // Column
                const otherCards = document.querySelector('#otherCards'); // Columns
                const round = document.querySelector('#round');

                clearInterval(timerInterval);
                remainingTime = timer;
                document.querySelector('#timer').innerText = "Temps restant: " + remainingTime + "s";
                timerInterval = setInterval(interval, 1000);

                // Reset content
                deck.innerHTML = "";
                myCard.innerHTML = "";
                otherCards.innerHTML = "";
                round.innerText = "";
                havePlayed = false;
                choice.querySelectorAll('button').forEach(button => button.disabled = false);

                // Show the current round
                round.innerText = "Manche " + (currentGame.currentRound + 1)

                // Show other player cards
                game.players
                    .filter(p => p.user.id !== ${user.id})
                    .forEach(p => {
                        const playerHand = new PlayerHand(p);
                        otherCards.innerHTML += playerHand.render({
                            textPosition: PlayerHand.TextPosition.TOP,
                            className: "column"
                        });
                    });

                // Show my card
                const me = game.players.find(p => p.user.id === ${user.id});

                const playerHand = new PlayerHand(me);
                myCard.innerHTML += playerHand.render({
                    textPosition: PlayerHand.TextPosition.BOTTOM,
                    className: "column"
                });

                // Show deck
                const deckCard = new Card(game.currentCard.color, game.currentCard.value);
                deck.innerHTML = deckCard.render();
            })

            wsgame.onMessage("end", (game) => {
                clearInterval(timerInterval);
                window.location.href = "${pageContext.request.contextPath}/game-statistics?id=${game.id}&endGame=true";
            })

            // Close handling
            wsgame.onClose(() => console.log("Disconnected from the server (GameWS)"));

            // Game
            let currentGame;

            // Player List
            let players = [];
            const playerList = document.querySelector('#playerList tbody');

            function updatePlayerList() {
                playerList.innerHTML = '';
                players.forEach(player => {
                    const tr = document.createElement('tr');
                    const td = document.createElement('td');
                    td.textContent = player.user.username;
                    tr.appendChild(td);
                    playerList.appendChild(tr);
                });
            }

            // Start Game Button
            document.querySelector('#start-game-button').addEventListener('click', () => {
                if (players.length < 2 || players.length > 4) {
                    alert("Il faut entre 2 et 4 joueurs pour démarrer la partie");
                    return;
                }

                const message = {type: "start", data: ""}
                wsgame.ws.send(JSON.stringify(message));
            });
        </script>


    </jsp:body>
</layout:base>
