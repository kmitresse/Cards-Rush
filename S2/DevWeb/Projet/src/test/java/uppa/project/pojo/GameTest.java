package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  static Game[] FIXTURE;

  @BeforeEach
  void setUp() {
    FIXTURE = new Game[] {
      new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 3,6, new ArrayList<Player>()),
      new Game(new BigDecimal(2), new Date(2024,3,26), Game.Difficulty.HARD, 52, 4,13, new ArrayList<Player>()),
      new Game(new BigDecimal(3), new Date(2023,9,18), Game.Difficulty.EASY, 5, 2,10, new ArrayList<Player>()),
    };
  }

  @Test
  void error_if_invalid_nbrounds() {
    //In constructor
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 0, 3,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, -1, 3,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, -5, 3,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 19, 3,6, new ArrayList<Player>()));

    //In setter
    for (int i = 0; i < FIXTURE.length; i++) {
      Game game = FIXTURE[i];
      assertThrows(IllegalArgumentException.class, () -> game.setNbRounds(0));
      assertThrows(IllegalArgumentException.class, () -> game.setNbRounds(-1));
      assertThrows(IllegalArgumentException.class, () -> game.setNbRounds(-5));
      assertThrows(IllegalArgumentException.class, () -> game.setNbRounds(19));
    }
  }

  @Test
  void error_if_invalid_nbColors() {
    //In constructor
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 0,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, -1,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, -5,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 5,6, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 17,6, new ArrayList<Player>()));

    //In setter
    for (int i = 0; i < FIXTURE.length; i++) {
      Game game = FIXTURE[i];
      assertThrows(IllegalArgumentException.class, () -> game.setNbColors(0));
      assertThrows(IllegalArgumentException.class, () -> game.setNbColors(-1));
      assertThrows(IllegalArgumentException.class, () -> game.setNbColors(-5));
      assertThrows(IllegalArgumentException.class, () -> game.setNbColors(5));
      assertThrows(IllegalArgumentException.class, () -> game.setNbColors(17));
    }
  }


  @Test
  void error_if_invalid_nbValuesPerColor() {
    //In constructor
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 3,0, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 3,-1, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 3,-5, new ArrayList<Player>()));
    assertThrows(IllegalArgumentException.class, () -> new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 3,14, new ArrayList<Player>()));

    //In setter
    for (int i = 0; i < FIXTURE.length; i++) {
      Game game = FIXTURE[i];
      assertThrows(IllegalArgumentException.class, () -> game.setNbValuesPerColor(0));
      assertThrows(IllegalArgumentException.class, () -> game.setNbValuesPerColor(-1));
      assertThrows(IllegalArgumentException.class, () -> game.setNbValuesPerColor(-5));
      assertThrows(IllegalArgumentException.class, () -> game.setNbValuesPerColor(14));
    }
  }
  @Test
  void test_HashCode() {
    // TODO Implement this method
  }

  @Test
  void get_Id() {
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(FIXTURE[i].getId(), new BigDecimal(i + 1));
    }
  }

  @Test
  void get_Created_At() {
    Date[] dates = new Date[]{new Date(2023,12,25),
                    new Date(2024,3,26),
                    new Date(2023,9,18)};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(FIXTURE[i].getCreatedAt(), dates[i]);
    }
  }

  @Test
  void get_Difficulty() {
    Game.Difficulty[] difficulties = new Game.Difficulty[]{
      Game.Difficulty.EASY,
      Game.Difficulty.HARD,
      Game.Difficulty.EASY };
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(FIXTURE[i].getDifficulty(), difficulties[i]);
    }
  }

  @Test
  void set_difficulty() {
    Game.Difficulty[] difficulties = new Game.Difficulty[]{
      Game.Difficulty.EASY,
      Game.Difficulty.HARD,
      Game.Difficulty.EASY };
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setDifficulty(Game.Difficulty.HARD);
      assertEquals(FIXTURE[i].getDifficulty(), Game.Difficulty.HARD);
      FIXTURE[i].setDifficulty(difficulties[i]);
      assertEquals(FIXTURE[i].getDifficulty(), difficulties[i]);
    }
  }

  @Test
  void get_nb_Rounds() {
    int[] nbRounds = new int[]{17, 52, 5};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(FIXTURE[i].getNbRounds(), nbRounds[i]);
    }
  }

  @Test
  void set_nb_Rounds() {
    int[] nbRounds = new int[]{17, 52, 5};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setNbRounds(10);
      assertEquals(FIXTURE[i].getNbRounds(), 10);
      FIXTURE[i].setNbRounds(nbRounds[i]);
      assertEquals(FIXTURE[i].getNbRounds(), nbRounds[i]);
    }
  }

  @Test
  void get_nb_Colors() {
    int[] nbColors = new int[]{3, 4, 2};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(FIXTURE[i].getNbColors(), nbColors[i]);
    }
  }

  @Test
  void set_nb_Colors() {
    int[] nbColors = new int[]{3, 4, 2};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setNbColors(5);
      assertEquals(FIXTURE[i].getNbColors(), 5);
      FIXTURE[i].setNbColors(nbColors[i]);
      assertEquals(FIXTURE[i].getNbColors(), nbColors[i]);
    }
  }

  @Test
  void getnbValuesPerColor() {
    int[] nbValuesPerColor = new int[]{6, 13, 10};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(FIXTURE[i].getNbValuesPerColor(), nbValuesPerColor[i]);
    }
  }

  @Test
  void setNbValuesPerColor() {
    int[] nbValuesPerColor = new int[]{6, 13, 10};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setNbValuesPerColor(8);
      assertEquals(FIXTURE[i].getNbValuesPerColor(), 8);
      FIXTURE[i].setNbValuesPerColor(nbValuesPerColor[i]);
      assertEquals(FIXTURE[i].getNbValuesPerColor(), nbValuesPerColor[i]);
    }
  }

  @Test
  void getPlayers() {
    // TODO Implement this method
    fail();
  }

  @Test
  void setPlayers() {
    // TODO Implement this method
    fail();
  }

  @Test
  void getNbPlayers() {
    // TODO Implement this method
    fail();
  }

  @Test
  void addPlayer() {
    // TODO Implement this method
    fail();
  }

  @Test
  void getDeck() {
    // TODO Implement this method
    fail();
  }

  @Test
  void sortPlayersByScore() {
    // TODO Implement this method
    fail();
  }

  @Test
  void to_string_return_the_right_format() {
      String[] expected = new String[]{
        "Game{id=1, createdAt=2023-12-25, players=[]}",
        "Game{id=2, createdAt=2024-03-26, players=[]}",
        "Game{id=3, createdAt=2023-09-18, players=[]}"
      };
      for (int i = 0; i < FIXTURE.length; i++) {
        assertEquals(FIXTURE[i].toString(), expected[i]);
      }
  }
}
