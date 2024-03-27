/*
 * Deck.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.pojo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Représentation d'un paquet de cartes
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public class Deck {

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
    cards = initializeDeck(nbColors, nbValues);
  }

  /**
   * @return l'ensemble de cartes du paquet
   */
  public ArrayList<Card> getCards() {
    return cards;
  }

  /**
   * Créé un paquet de cartes mélangé avec un nombre de couleurs et de valeurs donné
   *
   * @param nbColors nombre de couleurs (doit être compris entre 1 et le nombre de couleurs de {@link Card.Color})
   * @param nbValues nombre de valeurs (doit être compris entre 1 et le nombre de valeurs de {@link Card.Value})
   * @return un ensemble de cartes mélangées
   */
  private static ArrayList<Card> initializeDeck(int nbColors, int nbValues) {
    ArrayList<Card> cards = createSetOfCard(nbColors, nbValues);
    shuffleSetOfCard(cards);
    return cards;
  }

  /**
   * Créé un ensemble de cartes avec un nombre de couleurs et de valeurs donné
   *
   * @param nbColors nombre de couleurs à utiliser pour créer les cartes (doit être compris entre 1 et le nombre de couleurs de {@link Card.Color})
   * @param nbValues nombre de valeurs à utiliser pour créer les cartes (doit être compris entre 1 et le nombre de valeurs de {@link Card.Value})
   * @see Card.Color
   * @see Card.Value
   * @throws IllegalArgumentException si le nombre de couleurs ou de valeurs est incorrect
   * @return un ensemble de cartes
   */
  public static ArrayList<Card> createSetOfCard(int nbColors, int nbValues) throws IllegalArgumentException {
    ArrayList<Card> cards = new ArrayList<>(nbColors*nbValues);

    if (nbColors < 1 || nbColors > Card.Color.values().length) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre 1 et " + Card.Color.values().length);
    }
    if (nbValues < 1 || nbValues > Card.Value.values().length) {
      throw new IllegalArgumentException("Le nombre de valeurs doit être compris entre 1 et " + Card.Value.values().length);
    }

    for (int i = 0; i < nbColors; i++) {
      for (int j = 0; j < nbValues; j++) {
        cards.add(new Card(Card.Color.values()[i], Card.Value.values()[j]));
      }
    }
    return cards;
  }

  /**
   * Mélange les cartes d'un paquet
   *
   * @param cards ensemble de cartes à mélanger
   * @return un ensemble de cartes mélangées
   */
  public static void shuffleSetOfCard(ArrayList<Card> cards) {
    Collections.shuffle(cards);
  }

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
          continue;
        }
      }
    }
    return sameSize && counter == cards.size();
  }

}
