<%@tag description="component/modal/connected-users-list-modal" pageEncoding="UTF-8" %>
<%@taglib prefix="modal" tagdir="/WEB-INF/tags/components/modal" %>
<div id="user-list-modal" class="modal">
    <div class="modal-background"></div>
    <div class="modal-card modal-">
        <header class="modal-card-head">
            <p class="modal-card-title">${translator.translate('game_room_connected_users_list')}</p>
            <button class="delete" aria-label="close"></button>
        </header>
        <section class="modal-card-body">
            <table class="table is-fullwidth">
                <thead>
                <tr>
                    <th>${translator.translate('game_room_player_username')}</th>
                    <th>${translator.translate('game_room_player_played_games')}</th>
                    <th>${translator.translate('game_room_player_wins')}</th>
                    <th>${translator.translate('game_room_player_average_score')}</th>
                    <th>${translator.translate('game_room_player_correct_clicks')}</th>
                    <th>${translator.translate('game_room_player_rapid_clicks')}</th>
                    <th>${translator.translate('game_room_player_action')}</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </section>
        <footer class="modal-card-foot">
            <button class="button is-light">Fermer</button>
        </footer>
    </div>
</div>
