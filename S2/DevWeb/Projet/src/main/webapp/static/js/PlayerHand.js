import Card from "./Card.js";

export default class PlayerHand {
    static TextPosition = {
        TOP: "TOP",
        BOTTOM: "BOTTOM"
    }

    constructor(player) {
        this.player = player;
        this.card = new Card(player.currentCard.color, player.currentCard.value)
    }

    render({className, textPosition}) {
        if (!textPosition) textPosition = PlayerHand.TextPosition.TOP;

        const text = `
            <div class="pb-1 pt-1">
                <p class="has-text-centered has-text-white title is-4 mb-1">${this.player.user.username}</p>
                <p class="has-text-centered has-text-white title is-5">${this.player.score}</p>

            </div>
        `;

        return `
            <div class="${className} player-${this.player.user.id} is-flex is-flex-direction-column is-align-items-center">
                ${textPosition === PlayerHand.TextPosition.TOP ? text : ""}
                ${this.card.render()}
                ${textPosition === PlayerHand.TextPosition.BOTTOM ? text : ""}
            </div>
        `;

    }
}