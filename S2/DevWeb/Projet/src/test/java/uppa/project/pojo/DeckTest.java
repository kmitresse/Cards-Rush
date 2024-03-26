package uppa.project.pojo;

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
  void throw_error_on_creation_of_invalid_deck() {
    // Nombre de couleurs invalides
    assertThrows(IllegalArgumentException.class, () -> new Deck(0, 13));
    assertThrows(IllegalArgumentException.class, () -> new Deck(5, 13));

    // Nombre de valeurs invalides
    assertThrows(IllegalArgumentException.class, () -> new Deck(1, 0));
    assertThrows(IllegalArgumentException.class, () -> new Deck(1, 15));
  }

  @Test
  void get_cards() {
    // TODO Implement this method
    fail();
  }

  @Test
  void initialize_deck() {
    // TODO Implement this method
    fail();
  }

  @Test
  void get_same_size_when_shuffle() {
    // TODO Implement this method
    fail();
  }

  @Test
  void get_same_cards_when_shuffle(){
    // TODO Implement this method
    fail();
  }

}
