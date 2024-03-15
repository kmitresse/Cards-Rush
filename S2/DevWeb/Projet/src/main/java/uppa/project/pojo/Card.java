package uppa.project.pojo;

public class Card {
  /**
   * The color of the card:
   * Heart -> Coeur
   * Clubs -> Trèfle
   * Spades -> Pique
   * Diamonds -> Carreau
   */
  public enum Color{HEART, CLUBS, SPADES, DIAMONDS}

  public enum Value{ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
  private final Color color;

  private final Value value;

  public Card(Color color, Value value) {
    this.color = color;
    this.value = value;
  }

  public Color getColor() {
    return color;
  }

  public Value getValue() {
    return value;
  }
}
