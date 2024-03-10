package project.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {
  private Set<Card> cards;

  public Deck(){
    cards = new HashSet<>();
    initializeDeck();
    shuffleDeck();
  }

  private void initializeDeck() {
    for (Card.Color color : Card.Color.values()){
      for (Card.Value value : Card.Value.values()) {
        cards.add(new Card(color, value));
      }
    }
  }

  private void shuffleDeck() {
    List<Card> cardList = new ArrayList<>(cards);
    Collections.shuffle(cardList);
    cards = new HashSet<>(cardList);
  }
}
