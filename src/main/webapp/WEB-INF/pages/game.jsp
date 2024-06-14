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
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/websockets/game-management-websocket.js"></script>
        <script defer type="module" src="${pageContext.request.contextPath}/static/js/modal.js"></script>
    </jsp:attribute>
    <jsp:body>

        <component:hero>
            <div class="columns" id="gameWaiting">
                <div class="column">
                    <component:card title="${translator.translate('game_room_players_list')}">
                        <jsp:attribute name="footer">
                            <div class="card-footer">
                                <a id="leave-game-button" href="${pageContext.request.contextPath}/lobby" class="is-primary card-footer-item">${translator.translate('game_room_leave_game')}</a>
                                <a id="invite-player" data-target="#user-list-modal" class="card-footer-item modal-trigger">${translator.translate('game_room_add_player')}</a>
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
    </jsp:body>
</layout:base>
