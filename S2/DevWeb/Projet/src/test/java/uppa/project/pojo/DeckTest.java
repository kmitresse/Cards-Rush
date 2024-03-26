package uppa.project.pojo;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

  static Deck[] FIXTURE;

  @BeforeAll
  static void setup() {
    FIXTURE = new Deck[] {
      new Deck(4, 13),
      new Deck(4, 13),
    };
  }

  @Test
  void throw_error_on_creation_of_invalid_deck_with_an_invalid_color() {
    // Nombre de couleurs invalides
    assertThrows(IllegalArgumentException.class, () -> new Deck(0, 13));
    assertThrows(IllegalArgumentException.class, () -> new Deck(5, 10));
    assertThrows(IllegalArgumentException.class, () -> new Deck(8, 7));
    assertThrows(IllegalArgumentException.class, () -> new Deck(-1, 11));
    assertThrows(IllegalArgumentException.class, () -> new Deck(-6, 12));
  }

  @Test
  void throw_error_on_creation_of_invalid_deck_with_an_invalid_value() {
    // Nombre de valeurs invalides
    assertThrows(IllegalArgumentException.class, () -> new Deck(1, 0));
    assertThrows(IllegalArgumentException.class, () -> new Deck(2, 14));
    assertThrows(IllegalArgumentException.class, () -> new Deck(3, -2));
  }

  @Test
  void create_deck_get_the_right_number_cards() {
    assertEquals(52, new Deck(4,13).getCards().size());
    assertEquals(33, new Deck(3,11).getCards().size());
    assertEquals(26, new Deck(2,13).getCards().size());
    assertEquals(1, new Deck(1,1).getCards().size());
  }

  @Test
  void create_deck_get_the_right_number_colors() {
    assertEquals(4, new Deck(4,13).getCards().stream().map(Card::getColor).distinct().count());
    assertEquals(3, new Deck(3,10).getCards().stream().map(Card::getColor).distinct().count());
    assertEquals(2, new Deck(2,7).getCards().stream().map(Card::getColor).distinct().count());
    assertEquals(1, new Deck(1,1).getCards().stream().map(Card::getColor).distinct().count());
  }

  @Test
  void create_deck_get_the_right_number_values() {
    assertEquals(13, new Deck(4,13).getCards().stream().map(Card::getValue).distinct().count());
    assertEquals(10, new Deck(3,10).getCards().stream().map(Card::getValue).distinct().count());
    assertEquals(7, new Deck(2,7).getCards().stream().map(Card::getValue).distinct().count());
    assertEquals(1, new Deck(1,1).getCards().stream().map(Card::getValue).distinct().count());
  }

  @Test
  void shuffle_list_of_cards_set_them_in_an_aleatory_order(){
    ArrayList<Card> initialCards = new ArrayList<>();
    initialCards.add(new Card(Card.Color.HEART, Card.Value.ONE));
    initialCards.add(new Card(Card.Color.HEART, Card.Value.TWO));
    initialCards.add(new Card(Card.Color.HEART, Card.Value.THREE));
    initialCards.add(new Card(Card.Color.CLUBS, Card.Value.FOUR));
    initialCards.add(new Card(Card.Color.CLUBS, Card.Value.FIVE));
    initialCards.add(new Card(Card.Color.CLUBS, Card.Value.SIX));
    initialCards.add(new Card(Card.Color.DIAMONDS, Card.Value.SEVEN));
    initialCards.add(new Card(Card.Color.DIAMONDS, Card.Value.EIGHT));
    initialCards.add(new Card(Card.Color.DIAMONDS, Card.Value.NINE));
    initialCards.add(new Card(Card.Color.SPADES, Card.Value.TEN));
    initialCards.add(new Card(Card.Color.SPADES, Card.Value.JACK));
    initialCards.add(new Card(Card.Color.SPADES, Card.Value.QUEEN));
    ArrayList<Card> shuffledCards = new ArrayList<>(initialCards);
    ArrayList<Card> shuffledCards2 = new ArrayList<>(initialCards);
    Deck.shuffleSetOfCard(shuffledCards);
    Deck.shuffleSetOfCard(shuffledCards2);

    assertNotEquals(initialCards, shuffledCards);
    assertNotEquals(initialCards, shuffledCards2);
    assertNotEquals(shuffledCards, shuffledCards2);
  }

  @Test
  void initialize_deck_shuffle_cards() {
    Deck mockDeck = new Deck(4, 13);
    Deck mockDeck2 = new Deck(4, 13);
    assertNotEquals(mockDeck.getCards(), mockDeck2.getCards());
  }

  @Test
  void get_same_size_when_shuffle() {
    Deck mockDeck = new Deck(4, 13);
    int initialSize = mockDeck.getCards().size();
    Deck.shuffleSetOfCard(mockDeck.getCards());
    assertEquals(initialSize, mockDeck.getCards().size());
  }

  @Test
  void get_same_cards_when_shuffle(){
    Deck mockDeck = new Deck(4, 13);
    List<Card> initialCards = new ArrayList<>(mockDeck.getCards());
    Deck.shuffleSetOfCard(mockDeck.getCards());
    for (int i = 0; i < initialCards.size(); i++) {
      assertTrue(mockDeck.getCards().contains(initialCards.get(i)));
    }
  }

  @Test
  void create_multiple_deck_with_same_parameters_get_same_cards() {
    Deck mockDeck = new Deck(4, 13);
    Deck mockDeck2 = new Deck(4, 13);
    ArrayList<Card> cards = mockDeck.getCards();
    ArrayList<Card> cards2 = mockDeck2.getCards();
    int counter = 0;
    for (int i = 0; i < cards.size(); i++) {
      for(int j = 0; j < cards2.size(); j++) {
        if (cards.get(i).getColor() == cards2.get(j).getColor()
          && cards.get(i).getValue() == cards2.get(j).getValue()) {
          counter++;
          continue;
        }
      }
    }
    assertEquals(cards.size(), counter);
  }
}
