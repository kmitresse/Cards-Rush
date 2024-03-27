package uppa.project.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DeckTest {

  @Test
  void test_constructor() {
    new Deck(4, 13);
    new Deck(3, 10);
    new Deck(2, 7);
    new Deck(1, 1);
  }

  @Test
  void test_constructor_deckWithSameParametersHaveSameCards() {
    Deck mockDeck = new Deck(4, 13);
    Deck mockDeck2 = new Deck(4, 13);
    assertEquals(mockDeck, mockDeck2);
  }

  @Test
  void test_isValidDeck() {
    // Nombre de couleurs et de valeurs valides
    final int[][] TRUE_EXPECTED = {{4, 13}, {3, 10}, {2, 7},};

    for (int[] args : TRUE_EXPECTED) {
      assertTrue(Deck.isDeckValid(args[0], args[1]));
    }

    // Nombre de couleurs et de valeurs invalides
    final int[][] FALSE_EXPECTED = {{0, 13}, {5, 10}, {8, 7}, {-1, 11}, {-6, 12},};

    for (int[] args : FALSE_EXPECTED) {
      assertFalse(Deck.isDeckValid(args[0], args[1]));
    }
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidColor() {
    int[] INCORRECT_COLORS = {Integer.MIN_VALUE, 0, 5, 8, -1, -6, Integer.MAX_VALUE};
    for (int incorrect_color : INCORRECT_COLORS) {
      assertThrows(IllegalArgumentException.class, () -> new Deck(incorrect_color, 13));
    }
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidValues() {
    int[] INCORRECT_VALUES = {Integer.MIN_VALUE, -2, 0, 14, Integer.MAX_VALUE};
    for (int incorrect_value : INCORRECT_VALUES) {
      assertThrows(IllegalArgumentException.class, () -> new Deck(4, incorrect_value));
    }
  }

  @Test
  void test_getCards_size() {
    final HashMap<Deck, Integer> TESTS = new HashMap<>() {{
      put(new Deck(4, 13), 52);
      put(new Deck(3, 11), 33);
      put(new Deck(2, 7), 14);
      put(new Deck(1, 1), 1);
    }};
    for (Deck deck : TESTS.keySet()) {
      assertEquals(TESTS.get(deck), deck.getCards().size());
    }
  }

  @Test
  void test_getCards_returnTheRightNumberOfColors() {
    HashMap<Deck, Integer> TESTS = new HashMap<>() {{
      put(new Deck(4, 13), 4);
      put(new Deck(3, 10), 3);
      put(new Deck(2, 7), 2);
      put(new Deck(1, 1), 1);
    }};

    for (Deck deck : TESTS.keySet()) {
      int expected = TESTS.get(deck);
      int actual = (int)deck.getCards().stream().map(Card::getColor).distinct().count();
      assertEquals(expected, actual);
    }
  }

  @Test
  void test_getCards_returnTheRightNumberOfValues() {
    HashMap<Deck, Integer> TESTS = new HashMap<>() {{
      put(new Deck(4, 13), 13);
      put(new Deck(3, 10), 10);
      put(new Deck(2, 7), 7);
      put(new Deck(1, 1), 1);
    }};

    for (Deck deck : TESTS.keySet()) {
      int expected = TESTS.get(deck);
      int actual = (int)deck.getCards().stream().map(Card::getValue).distinct().count();
      assertEquals(expected, actual);
    }
  }

  @Test
  void test_shuffle() {
    Deck mockDeck1 = new Deck(4,6);
    Deck mockDeck2 = new Deck(4,6);
    Deck mockDeck3 = new Deck(4,6);

    mockDeck1.shuffle();
    mockDeck2.shuffle();
    mockDeck3.shuffle();
    assertNotEquals(mockDeck1.getCards(), mockDeck2.getCards());
    assertNotEquals(mockDeck1.getCards(), mockDeck3.getCards());
    assertNotEquals(mockDeck2.getCards(), mockDeck3.getCards());
  }

  @Test
  void test_shuffle_deckSizeHasNotChange() {
    Deck mockDeck = new Deck(4, 13);
    int initialSize = mockDeck.getCards().size();
    mockDeck.shuffle();
    assertEquals(initialSize, mockDeck.getCards().size());
  }

  @Test
  void test_shuffle_deckHaveSameCards() {
    Deck mockDeck = new Deck(4, 13);
    Deck mockDeck2 = new Deck(4,13);
    mockDeck2.shuffle();
    assertEquals(mockDeck, mockDeck2);
  }
}