<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<layout:base>

    <component:hero>
        <div class="columns">
            <div class="column">
                <component:card title="Liste des joueurs dans la partie">
                    <jsp:attribute name="footer">
                        <a data-target="#user-list-modal" class="card-footer-item modal-trigger">Ajouter</a>
                    </jsp:attribute>
                </component:card>
            </div>
            <div class="column">
                <component:card title="Partie n°${pageContext.request.getParameter('id')}">
                    <jsp:useBean id="game" scope="request" type="uppa.project.database.pojo.Game"/>

                    <p><strong>Créé le:</strong> ${game.createdAt.toLocaleString()}</p>
                    <p><strong>Difficulté:</strong> ${game.difficulty}</p>
                    <p><strong>Nombre de tours:</strong> ${game.nbRounds}</p>
                    <p><strong>Valeurs par couleur:</strong> ${game.nbValuesPerColor}</p>
                    <p><strong>Nombre de couleurs:</strong> ${game.nbColors}</p>
                </component:card>
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
                    $tdAction.appendChild($button);

                    $tr.appendChild($tdUsername);
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
            ws.onOpen(() => console.log("Connected to the server"));
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

</layout:base>