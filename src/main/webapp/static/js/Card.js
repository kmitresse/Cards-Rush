export default class Card {
    static Color = {
        DIAMONDS: "DIAMONDS",
        HEART: "HEART",
        SPADES: "SPADES",
        CLUBS: "CLUBS",
    }

    constructor(color, value) {
        this.color = color;
        this.value = value;
    }

    getValueColor() {
        return (this.color === Card.Color.DIAMONDS || this.color === Card.Color.HEART)
            ? 'red' : 'black'
    }

    render() {
        const className = [
            "card-play",
            this.color.toLowerCase(),
            `${this.getValueColor()}_${this.value.toLowerCase()}`
        ]

        return `<div class="${className.join(" ")}"></div>`
    }
}