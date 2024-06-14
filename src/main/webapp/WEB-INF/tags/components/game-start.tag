<%@tag description="component/game-start" pageEncoding="UTF-8" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>
<jsp:useBean id="game" scope="request" type="uppa.project.database.pojo.Game"/>

<div id="gameStarted" style="display:none;">
    <div class="columns" id="otherCards"></div>
    <div class="columns">
        <div class="column is-flex is-flex-direction-column is-align-items-center" id="deck"></div>
        <div class="column is-one-quarter is-justify-content-center" id="choice"
             style="position: absolute; right: 0; z-index: 9999">
            <div class="buttons is-flex-direction-column">
                <p id="round-info" class="title has-text-white">${translator.translate('game_round')} <span id="round"></span> </p>
                <p id="timer-info" class="subtitle has-text-white">${translator.translate('game_timer')} <span id="timer"></span></p>
                <button class="button is-fullwidth" data-value="COLOR_VALUE">${translator.translate('game_same_card')}</button>
                <button class="button is-fullwidth" data-value="COLOR">${translator.translate('game_same_color')}</button>
                <button class="button is-fullwidth" data-value="VALUE">${translator.translate('game_same_value')}</button>
                <button class="button is-fullwidth" data-value="NONE">${translator.translate('game_none')}</button>
            </div>
        </div>
    </div>
    <div class="columns is-centered" id="myCard">
    </div>
</div>
