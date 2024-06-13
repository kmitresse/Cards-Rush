<%@tag description="component/game-room-information" pageEncoding="UTF-8" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<jsp:useBean id="game" scope="request" type="uppa.project.database.pojo.Game"/>

<input type="hidden" id="game-id" value="${game.id}">

<p><strong>${translator.translate('game_information_created_at')}</strong> ${game.createdAt.toLocaleString()}</p>
<p><strong>${translator.translate('game_information_difficulty')}</strong> ${game.difficulty}</p>
<p><strong>${translator.translate('game_information_rounds_number')}</strong> ${game.nbRounds}</p>
<p><strong>${translator.translate('game_information_rounds_duration')}</strong> ${game.timer} ${translator.translate('timer_unit')}</p>
<p><strong>${translator.translate('game_information_deck_color_number')}</strong> ${game.nbColors}</p>
<p><strong>${translator.translate('game_information_deck_value_number')}</strong> ${game.nbValuesPerColor}</p>
