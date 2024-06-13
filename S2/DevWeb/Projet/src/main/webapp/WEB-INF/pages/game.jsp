<%@ page import="uppa.project.web.translation.Translator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="modal" tagdir="/WEB-INF/tags/components/modal" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>


<layout:base title="${translator.translate('game_room_title')}">
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/card.css">
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/websockets/player-management-websocket.js"></script>
    </jsp:attribute>
    <jsp:body>

        <component:hero>
            <div class="columns" id="gameWaiting">
                <div class="column">
                    <component:card title="${translator.translate('game_room_players_list')}">
                        <jsp:attribute name="footer">
                            <div class="card-footer">
                                <a id="leave-game-button" href="${pageContext.request.contextPath}/lobby" class="is-primary card-footer-item">${translator.translate('game_room_leave_game')}</a>
                                <a data-target="#user-list-modal" class="card-footer-item modal-trigger">${translator.translate('game_room_add_player')}</a>
                                <a id="start-game-button"  class="is-primary card-footer-item">${translator.translate('game_room_start_game')}</a>
                            </div>
                        </jsp:attribute>
                        <jsp:body>
                            <component:game-player-list/>
                        </jsp:body>
                    </component:card>
                </div>
                <div class="column">
                    <component:card title="${translator.translate('game_information_title')}">
                       <component:game-room-information/>
                    </component:card>
                </div>
            </div>
            <component:game-start/>
        </component:hero>

        <!-- Liste des utilisateurs dans le lobby -->
        <modal:connected-users-list-modal/>

        <jsp:useBean id="user" scope="session" type="uppa.project.database.pojo.User"/>
        <script defer type="module">

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
                    const $tdScore = document.createElement('td');
                    const $tdRightClick = document.createElement('td');
                    const $tdRapidClick = document.createElement('td');
                    const $tdAction = document.createElement('td');
                    const $button = document.createElement('button');

                    $button.classList.add('button', 'is-primary', 'is-light');
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
                    $tdScore.textContent = user.scoreRate + "%";
                    $tdRightClick.textContent = user.rigthClickPercentRate + "%";
                    $tdRapidClick.textContent = user.rapidClickPercentRate + "%";
                    $tdAction.appendChild($button);

                    $tr.appendChild($tdUsername);
                    $tr.appendChild($tdNbPlayedGame);
                    $tr.appendChild($tdNbWins);
                    $tr.appendChild($tdScore);
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
            document.querySelector('#timer').innerText = remainingTime + "s";

            const interval = () => {
                remainingTime--;

                document.querySelector('#timer').innerText = remainingTime + "s";

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
                round.innerText = (currentGame.currentRound + 1)

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
                document.querySelector('#timer').innerText = remainingTime + "s";
                timerInterval = setInterval(interval, 1000);

                // Reset content
                deck.innerHTML = "";
                myCard.innerHTML = "";
                otherCards.innerHTML = "";
                round.innerText = "";
                havePlayed = false;
                choice.querySelectorAll('button').forEach(button => button.disabled = false);

                // Show the current round
                round.innerText = (currentGame.currentRound + 1)

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
