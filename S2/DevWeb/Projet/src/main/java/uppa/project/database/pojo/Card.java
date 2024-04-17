/*
 * Card.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.pojo;

import jakarta.persistence.Transient;

/**
 * Représentation d'une carte
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public class Card {

  /**
   * Couleurs disponibles pour les cartes
   */
  public enum Color{HEART, CLUBS, SPADES, DIAMONDS}

  /**
   * Valeurs disponibles pour les cartes
   */
  public enum Value{ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

  /**
   * Couleur de la carte
   */
  private final Color color;

  /**
   * Valeur de la carte
   */
  private final Value value;

  /**
   * Constructeur par défaut
   *
   * @param color couleur de la carte
   * @param value valeur de la carte
   *
   * @see Color
   * @see Value
   */
  public Card(Color color, Value value) {
    if (color == null || value == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = color;
    this.value = value;
  }

  /**
   * @return la couleur de la carte
   */
  public Color getColor() {
    return color;
  }

  /**
   * @return la valeur de la carte
   */
  public Value getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Card{" +
      "color=" + color +
      ", value=" + value +
      '}';
  }
}