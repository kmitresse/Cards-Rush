package uppa.project.pojo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

  static Card[] FIXTURE;

  @BeforeAll
  static void setup() {
    FIXTURE = new Card[]{
      // Card Value
      new Card(Card.Color.HEART, Card.Value.ONE),
      new Card(Card.Color.HEART, Card.Value.TWO),
      new Card(Card.Color.HEART, Card.Value.THREE),
      new Card(Card.Color.CLUBS, Card.Value.FOUR),
      new Card(Card.Color.CLUBS, Card.Value.FIVE),
      new Card(Card.Color.CLUBS, Card.Value.SIX),
      new Card(Card.Color.DIAMONDS, Card.Value.SEVEN),
      new Card(Card.Color.DIAMONDS, Card.Value.EIGHT),
      new Card(Card.Color.DIAMONDS, Card.Value.NINE),
      new Card(Card.Color.SPADES, Card.Value.TEN),
      new Card(Card.Color.SPADES, Card.Value.JACK),
      new Card(Card.Color.SPADES, Card.Value.QUEEN),
      new Card(Card.Color.HEART, Card.Value.KING),

      // Card Color
      new Card(Card.Color.HEART, Card.Value.ONE),
      new Card(Card.Color.CLUBS, Card.Value.ONE),
      new Card(Card.Color.DIAMONDS, Card.Value.ONE),
      new Card(Card.Color.SPADES, Card.Value.ONE),
    };
  }

  @Test
  void getColor() {
    Card.Color[] expected = new Card.Color[] {
      Card.Color.HEART,
      Card.Color.HEART,
      Card.Color.HEART,
      Card.Color.CLUBS,
      Card.Color.CLUBS,
      Card.Color.CLUBS,
      Card.Color.DIAMONDS,
      Card.Color.DIAMONDS,
      Card.Color.DIAMONDS,
      Card.Color.SPADES,
      Card.Color.SPADES,
      Card.Color.SPADES,
      Card.Color.HEART,

      Card.Color.HEART,
      Card.Color.CLUBS,
      Card.Color.DIAMONDS,
      Card.Color.SPADES,
    };

    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getColor());
    }
  }

  @Test
  void getValue() {
    Card.Value[] expected = new Card.Value[] {
      Card.Value.ONE,
      Card.Value.TWO,
      Card.Value.THREE,
      Card.Value.FOUR,
      Card.Value.FIVE,
      Card.Value.SIX,
      Card.Value.SEVEN,
      Card.Value.EIGHT,
      Card.Value.NINE,
      Card.Value.TEN,
      Card.Value.JACK,
      Card.Value.QUEEN,
      Card.Value.KING,

      Card.Value.ONE,
      Card.Value.ONE,
      Card.Value.ONE,
      Card.Value.ONE,
    };

    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getValue());
    }
  }
}
