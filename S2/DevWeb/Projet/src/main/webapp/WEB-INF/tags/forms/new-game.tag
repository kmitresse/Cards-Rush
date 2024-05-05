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
</style>
