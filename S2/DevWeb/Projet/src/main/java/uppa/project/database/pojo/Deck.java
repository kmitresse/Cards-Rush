/*
 * Deck.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.pojo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Représentation d'un paquet de cartes
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public class Deck {

  public static final int NB_COLORS_MIN = 2;

  public static final int NB_COLORS_MAX = Card.Color.values().length;

  public static final int NB_VALUES_PER_COLOR_MIN = 2;

  public static final int NB_VALUES_PER_COLOR_MAX = Card.Value.values().length;

  /**
   * Ensemble de cartes du paquet
   * @see Card
   */
  private ArrayList<Card> cards;

  /**
   * Constructeur par défaut
   *
   * @param nbColors nombre de couleurs (doit être compris entre 1 et le nombre de couleurs de {@link Card.Color})
   * @param nbValues nombre de valeurs (doit être compris entre 1 et le nombre de valeurs de {@link Card.Value})
   * @see Card.Color
   * @see Card.Value
   */
  public Deck(int nbColors, int nbValues) {
    if (!Deck.isDeckValid(nbColors, nbValues)) {
      throw new IllegalArgumentException("Nombre de couleurs et/ou nombre de valeurs invalide(s)");
    }

    this.cards = new ArrayList<>(nbColors * nbValues);

    for (int nbCard = 0; nbCard < nbColors * nbValues; nbCard++) {
      int color = nbCard % nbColors;
      int value = nbCard / nbColors;

      cards.add(new Card(Card.Color.values()[color], Card.Value.values()[value]));
    }
  }

  /**
   * Prédicat qui vérifie si un Deck est correctement initialisé
   *
   * @param nbColors Nombre de couleurs {@link Card.Color}
   * @param nbValues Nombre de valeurs {@link Card.Value}
   * @return true si le prédicat est vérifié, false sinon
   */
  public static boolean isDeckValid(int nbColors, int nbValues) {
    return NB_COLORS_MIN <= nbColors && nbColors <= NB_COLORS_MAX
      && NB_VALUES_PER_COLOR_MIN <= nbValues && nbValues <= NB_VALUES_PER_COLOR_MAX;
  }

  /**
   * @return l'ensemble de cartes du paquet
   */
  public ArrayList<Card> getCards() {
    return cards;
  }

  /**
   * Mélange les cartes d'un paquet
   */
  public void shuffle() {
    Collections.shuffle(this.cards);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Deck deck = (Deck) o;
    boolean sameSize = cards.size() == deck.cards.size();
    int counter = 0;
    for (int i = 0; i < cards.size(); i++) {
      for (int j = 0; j < deck.cards.size(); j++) {
        if (cards.get(i).getColor() == deck.cards.get(j).getColor()
          && cards.get(i).getValue() == deck.cards.get(j).getValue()) {
          counter++;
          break;
        }
      }
    }
    return sameSize && counter == cards.size();
  }

}
