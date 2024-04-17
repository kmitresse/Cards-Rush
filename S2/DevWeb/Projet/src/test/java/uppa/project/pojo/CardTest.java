package uppa.project.pojo;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import uppa.project.database.pojo.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {

  @Test
  void test_constructor() {

    // Card with correct Value
    new Card(Card.Color.HEART, Card.Value.ACE);
    new Card(Card.Color.HEART, Card.Value.TWO);
    new Card(Card.Color.HEART, Card.Value.THREE);
    new Card(Card.Color.CLUBS, Card.Value.FOUR);
    new Card(Card.Color.CLUBS, Card.Value.FIVE);
    new Card(Card.Color.CLUBS, Card.Value.SIX);
    new Card(Card.Color.DIAMONDS, Card.Value.SEVEN);
    new Card(Card.Color.DIAMONDS, Card.Value.EIGHT);
    new Card(Card.Color.DIAMONDS, Card.Value.NINE);
    new Card(Card.Color.SPADES, Card.Value.TEN);
    new Card(Card.Color.SPADES, Card.Value.JACK);
    new Card(Card.Color.SPADES, Card.Value.QUEEN);
    new Card(Card.Color.HEART, Card.Value.KING);

    // Card with correct Color
    new Card(Card.Color.HEART, Card.Value.ACE);
    new Card(Card.Color.CLUBS, Card.Value.ACE);
    new Card(Card.Color.DIAMONDS, Card.Value.ACE);
    new Card(Card.Color.SPADES, Card.Value.ACE);

    // Invalid Card Color
    assertThrows(IllegalArgumentException.class, () -> new Card(null, Card.Value.ACE));

    // Invalid Card Value
    assertThrows(IllegalArgumentException.class, () -> new Card(Card.Color.HEART, null));
  }

  @Test
  void test_getColor() {
    final HashMap<Card, Card.Color> TESTS = new HashMap<>() {{
      put(new Card(Card.Color.HEART, Card.Value.ACE), Card.Color.HEART);
      put(new Card(Card.Color.CLUBS, Card.Value.TWO), Card.Color.CLUBS);
      put(new Card(Card.Color.DIAMONDS, Card.Value.THREE), Card.Color.DIAMONDS);
      put(new Card(Card.Color.SPADES, Card.Value.FOUR), Card.Color.SPADES);
    }};

    for (Card card : TESTS.keySet()) {
      assertEquals(TESTS.get(card), card.getColor());
    }
  }

  @Test
  void test_getValue() {
    final HashMap<Card, Card.Value> TESTS = new HashMap<>() {{
      put(new Card(Card.Color.DIAMONDS, Card.Value.ACE), Card.Value.ACE);
      put(new Card(Card.Color.DIAMONDS, Card.Value.TWO), Card.Value.TWO);
      put(new Card(Card.Color.DIAMONDS, Card.Value.THREE), Card.Value.THREE);
      put(new Card(Card.Color.DIAMONDS, Card.Value.FOUR), Card.Value.FOUR);
      put(new Card(Card.Color.DIAMONDS, Card.Value.FIVE), Card.Value.FIVE);
      put(new Card(Card.Color.DIAMONDS, Card.Value.SIX), Card.Value.SIX);
      put(new Card(Card.Color.DIAMONDS, Card.Value.SEVEN), Card.Value.SEVEN);
      put(new Card(Card.Color.DIAMONDS, Card.Value.EIGHT), Card.Value.EIGHT);
      put(new Card(Card.Color.DIAMONDS, Card.Value.NINE), Card.Value.NINE);
      put(new Card(Card.Color.DIAMONDS, Card.Value.TEN), Card.Value.TEN);
      put(new Card(Card.Color.DIAMONDS, Card.Value.JACK), Card.Value.JACK);
      put(new Card(Card.Color.DIAMONDS, Card.Value.QUEEN), Card.Value.QUEEN);
      put(new Card(Card.Color.DIAMONDS, Card.Value.KING), Card.Value.KING);
    }};

    for (Card card : TESTS.keySet()) {
      assertEquals(TESTS.get(card), card.getValue());
    }
  }

  @Test
  void test_toString() {

    final HashMap<Card, String> TESTS = new HashMap<>() {{
      put(new Card(Card.Color.HEART, Card.Value.ACE), "Card{color=HEART, value=ACE}");
      put(new Card(Card.Color.CLUBS, Card.Value.TWO), "Card{color=CLUBS, value=TWO}");
      put(new Card(Card.Color.DIAMONDS, Card.Value.THREE), "Card{color=DIAMONDS, value=THREE}");
      put(new Card(Card.Color.SPADES, Card.Value.FOUR), "Card{color=SPADES, value=FOUR}");
    }};

    for (Card card : TESTS.keySet()) {
      assertEquals(TESTS.get(card), card.toString());
    }
  }

  @Test
  void test_CardValue() {

    // Expect the right number of values
    assertEquals(13, Card.Value.values().length);

    // Expect the right name and ordinal for each value
    final String[] EXPECTED_NAMES = new String[]{
      "ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"
    };

    for (int index = 0; index < EXPECTED_NAMES.length; index++) {
      String expectedName = EXPECTED_NAMES[index];
      Card.Value currentValue = Card.Value.values()[index];

      assertEquals(index, currentValue.ordinal());     // Ordinal
      assertEquals(expectedName, currentValue.name()); // Name
    }
  }

  @Test
  void test_CardColor() {
    // Expect the right number of colors
    assertEquals(4, Card.Color.values().length);

    // Expect the right name and ordinal for each color
    final String[] EXPECTED_NAMES = new String[]{"HEART", "CLUBS", "SPADES", "DIAMONDS"};

    for (int index = 0; index < Card.Color.values().length; index++) {
      String expectedName = EXPECTED_NAMES[index];
      Card.Color currentColor = Card.Color.values()[index];

      assertEquals(index, currentColor.ordinal());     // Ordinal
      assertEquals(expectedName, currentColor.name()); // Name
    }
  }

}
