<%@ tag import="uppa.project.database.pojo.Deck" %>
<%@ tag import="uppa.project.database.pojo.Game" %>
<%@ tag import="uppa.project.web.translation.Translator" %>
<%@tag description="form/new-game" pageEncoding="UTF-8" %>
<% Translator translator = (Translator) request.getSession().getAttribute("translator"); %>

<%@attribute name="back_button" fragment="true" %>

<form id="new-game-form" action="${pageContext.request.contextPath}/new" method="post">

    <h2 class="title is-5">${translator.translate('new_game_configuration_global_parameters')}</h2>
    <div class="field">
        <label class="label">${translator.translate('new_game_configuration_global_parameters_difficulty')}</label>
        <div class="control columns">
            <div class="column">
                <label class="radio button is-fullwidth is-primary is-light">
                    <input type="radio" name="difficulty" value="EASY" checked/>
                    ${translator.translate('difficulty_easy')}
                </label>
            </div>
            <div class="column">
                <label class="radio button is-fullwidth is-light">
                    <input type="radio" value="HARD" name="difficulty"/>
                    ${translator.translate('difficulty_hard')}
                </label>
            </div>
        </div>
    </div>

    <h2 class="title is-5">${translator.translate('new_game_configuration_rounds_parameters')}</h2>
    <div class="columns">
        <div class="field column">
            <label class="label" for="nbRounds">${translator.translate('new_game_configuration_rounds_parameters_number')}</label>
            <input class="input" required type="number" id="nbRounds" name="nbRounds"
                   value="${Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX}"
                   min="${Deck.NB_COLORS_MIN * Deck.NB_VALUES_PER_COLOR_MIN}"
                   max="${Deck.NB_COLORS_MAX * Deck.NB_VALUES_PER_COLOR_MAX}">
        </div>
        <div class="column field">
            <label class="label" for="timer">${translator.translate('new_game_configuration_rounds_parameters_duration')}</label>
            <input class="input" required type="number" id="timer" name="timer"
                   value="${Game.TIMER_MIN}"
                   min="${Game.TIMER_MIN}"
                   max="${Game.TIMER_MAX}">
        </div>
    </div>

    <h2 class="title is-5">${translator.translate('new_game_configuration_Deck_parameters')}</h2>
    <div class="columns">
        <div class="column field">
            <label class="label" for="nbColors">
                ${translator.translate('new_game_configuration_Deck_parameters_color_number')} <span class="tag is-medium is-light is-primary" id="tooltip-colors">${Deck.NB_COLORS_MAX}</span>
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
                ${translator.translate('new_game_configuration_Deck_parameters_value_number')} <span class="tag is-medium is-light is-primary" id="tooltip-values">${Deck.NB_VALUES_PER_COLOR_MAX}</span>
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
            <input type="submit" class="button is-fullwidth is-primary has-text-white" value="${translator.translate('new_game_create')}"/>
        </div>
    </div>
</form>

<style>
</style>
