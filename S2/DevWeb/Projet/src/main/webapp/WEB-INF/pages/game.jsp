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
                TODO Liste des utilisateurs
            </section>
            <footer class="modal-card-foot">
                <button class="button is-success">Fermer</button>
            </footer>
        </div>
    </div>

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

</layout:base>