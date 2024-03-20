package uppa.project.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {
  private Set<Card> cards;

  public Deck(int nbColors, int nbValues){
    cards = new HashSet<>();
    initializeDeck(nbColors, nbValues);
    shuffleDeck();
  }

  private void initializeDeck(int nbColors, int nbValues){
    for (int i = 0; i < nbColors; i++) {
      for (int j = 0; j < nbValues; j++) {
        cards.add(new Card(Card.Color.values()[i], Card.Value.values()[j]));
      }
    }
  }

  private void shuffleDeck() {
    List<Card> cardList = new ArrayList<>(cards);
    Collections.shuffle(cardList);
    cards = new HashSet<>(cardList);
  }
}
