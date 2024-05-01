<%@ tag import="uppa.project.database.pojo.Deck" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@tag description="form/new-game" pageEncoding="UTF-8" %>

<%@attribute name="back_button" fragment="true" %>

<form id="new-game-form" action="${pageContext.request.contextPath}/new" method="post">

    <h2 class="title is-5">Paramètres Généraux</h2>
    <div class="field">
        <label class="label">Difficulté</label>
        <div class="control columns">
            <div class="column">
                <label class="radio button is-fullwidth is-primary is-light">
                    <input type="radio" name="difficulty" value="EASY" checked/>
                    Facile
                </label>
            </div>
            <div class="column">
                <label class="radio button is-fullwidth is-light">
                    <input type="radio" value="HARD" name="difficulty"/>
                    Difficile
                </label>
            </div>
        </div>
    </div>

    <h2 class="title is-5">Paramètres des Manches</h2>
    <div class="columns">
        <div class="field column">
            <label class="label" for="nbRounds">Nombre de manches</label>
            <input class="input" required type="number" id="nbRounds" name="nbRounds"
                   value="${Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX}"
                   min="${Deck.NB_COLORS_MIN * Deck.NB_VALUES_PER_COLOR_MIN}"
                   max="${Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX}">
        </div>
        <div class="column field">
            <label class="label" for="timer">Durée d'une manche</label>
            <input class="input" required type="number" id="timer" name="timer"
                   value="${Game.TIMER_MIN}"
                   min="${Game.TIMER_MIN}"
                   max="${Game.TIMER_MAX}">
        </div>
    </div>

    <h2 class="title is-5">Paramètres du Deck</h2>
    <div class="columns">
        <div class="column field">
            <label class="label" for="nbColors">
                Nombre de couleurs: <span class="tag is-medium is-light is-primary" id="tooltip-colors">${Deck.NB_COLORS_MAX}</span>
            </label>
            <div class="control is-flex is-1">
                <span class="mr-1 tag"><strong>${Deck.NB_COLORS_MIN}</strong></span>
                <input type="range" required id="nbColors" name="nbColors" class="is-flex-grow-1" data-tooltip="#tooltip-colors"
                       value="${Deck.NB_COLORS_MAX}"
                       min="${Deck.NB_COLORS_MIN}"
                       max="${Deck.NB_COLORS_MAX}">
                <span class="ml-1 tag"><strong>${Deck.NB_COLORS_MAX}</strong></span>
            </div>
        </div>
        <div class="column field">
            <label class="label" for="nbValues">
                Nombre de valeurs: <span class="tag is-medium is-light is-primary" id="tooltip-values">${Deck.NB_VALUES_PER_COLOR_MAX}</span>
            </label>
            <div class="control is-flex is-1">
                <span class="mr-1 tag"><strong>${Deck.NB_VALUES_PER_COLOR_MIN}</strong></span>
                <input type="range" required id="nbValues" name="nbValues" class="is-flex-grow-1" data-tooltip="#tooltip-values"
                       value="${Deck.NB_VALUES_PER_COLOR_MAX}"
                       min="${Deck.NB_VALUES_PER_COLOR_MIN}"
                       max="${Deck.NB_VALUES_PER_COLOR_MAX}">
                <span class="ml-1 tag"><strong>${Deck.NB_VALUES_PER_COLOR_MAX}</strong></span>
            </div>
        </div>
    </div>

    <div class="columns">
        <jsp:invoke fragment="back_button"/>
        <div class="column">
            <input type="submit" class="button is-fullwidth is-primary has-text-white" value="Créer la partie"/>
        </div>
    </div>
</form>

<style>
    label.radio.button > input[type="radio"] {
        display: none;
    }
</style>

<script defer type="module">
    const nbRound = document.querySelector("input[name='nbRounds']");
    const nbValues = document.querySelector("input[name='nbValues']");
    const nbColors = document.querySelector("input[name='nbColors']");
    const rangeInputs = document.querySelectorAll("input[type='range']");
    rangeInputs.forEach(input => {
        const tooltip = document.querySelector(input.dataset.tooltip);
        input.addEventListener("input", () => {
          tooltip.innerHTML = input.value
          nbRound.max = nbValues.value * nbColors.value;
          nbRound.value = parseInt(nbRound.value) > parseInt(nbRound.max) ? nbRound.max : nbRoundtmp;
        });
    });

    const radioButtons = document.querySelectorAll('input[type="radio"]');
    radioButtons.forEach(radio => {
        radio.addEventListener('change', () => {
            radioButtons.forEach(radio => radio.parentElement.classList.remove('is-primary'));
            radio.parentElement.classList.add('is-primary');
        });
    });

    const form = document.getElementById('new-game-form');

    form.addEventListener('submit', evt => {
        evt.preventDefault();

        const {action, method} = form;

        const url = new URL(action);
        const formData = new FormData(form);
        for (const [key, value] of formData.entries()) {
            url.searchParams.append(key, value);
        }

        fetch(url, {headers: {"Content-Type": "application/json"}, method})
            .then(res => res.json())
            .then(data => {
                if (data.code !== 200) throw new Error(data.message);

                // Redirection vers la page de jeu
                window.location.href = "${pageContext.request.contextPath}/game?id=" + data.message;
            })
            .catch((error) => {
                console.log(error)

                // Notification
                const notification = document.createElement("div");
                notification.classList.add("notification", "is-danger");

                const notificationTitle = document.createElement("p");
                notificationTitle.classList.add("title", "is-6");
                notificationTitle.innerHTML = "Erreur";

                const notificationIcon = document.createElement("span");
                notificationIcon.classList.add("icon");
                notificationIcon.innerHTML = "<i class='fas fa-exclamation-triangle'></i>";

                const notificationMessage = document.createElement("p");
                notificationMessage.classList.add("subtitle", "is-6");
                notificationMessage.innerHTML = error.message;

                notificationTitle.appendChild(notificationIcon);
                notification.appendChild(notificationTitle);
                notification.appendChild(notificationMessage);
                document.body.appendChild(notification);

                // Retrait de la notification et des animations après 5 secondes
                setTimeout(() => notification.remove(), 5010);
            });
    });
</script>
