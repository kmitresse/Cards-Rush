package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

  Game[] fixture;
  static Player[][] playersFixture;

  @BeforeAll
  static void beforeAll(){
    playersFixture = new Player[][]{
      {new Player(new BigDecimal(1), new Game(Game.Difficulty.EASY, 10, 17, 3, 6), new User(), 10, true, 5, 5, 5)},
      {new Player(new BigDecimal(2), new Game(Game.Difficulty.EASY, 10, 17, 3, 6), new User(), 15, true, 5, 5, 5),
       new Player(new BigDecimal(3), new Game(Game.Difficulty.EASY, 10, 17, 3, 6), new User(), 20, true, 5, 5, 5)},
      {new Player(new BigDecimal(4), new Game(Game.Difficulty.EASY, 15, 17, 3, 6), new User(), 10, true, 5, 5, 5),
       new Player(new BigDecimal(5), new Game(Game.Difficulty.EASY, 15, 17, 3, 6), new User(), 30, true, 5, 5, 5),
       new Player(new BigDecimal(6), new Game(Game.Difficulty.EASY, 15, 17, 3, 6), new User(), 20, true, 5, 5, 5)}
    };
  }

  @BeforeEach
  void beforeEach() {
    fixture = new Game[]{
      new Game(new BigDecimal(1), new Date(2024 - 1900, 5, 6), Game.Difficulty.EASY, 50,  17, 3, 6, new ArrayList<>(Arrays.asList(playersFixture[0]))),
      new Game(new BigDecimal(2), new Date(2023 - 1900, 7, 9), Game.Difficulty.HARD, 10, 28, 4, 13, new ArrayList<>(Arrays.asList(playersFixture[1]))),
      new Game(new BigDecimal(3), new Date(2022 - 1900, 11, 12), Game.Difficulty.EASY, 15, 16, 2, 9, new ArrayList<>(Arrays.asList(playersFixture[2])))
    };
  }

  @Test
  void test_constructor() {
    new Game();
    new Game(Game.Difficulty.EASY, 10, 17, 3, 6);
    new Game(new BigDecimal(1), new Date(2023, 12, 25), Game.Difficulty.EASY,15, 17, 3, 6, new ArrayList<Player>());
    new Game(new BigDecimal(2), new Date(2024, 3, 26), Game.Difficulty.HARD, 20, 52, 4, 13, new ArrayList<Player>());
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidValues() {
    int[] INCORRECT_VALUES = {Integer.MIN_VALUE, -2, 0, 14, Integer.MAX_VALUE};
    for (int incorrect_value : INCORRECT_VALUES) {
      assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,10, 4 * incorrect_value, 4, incorrect_value));
    }
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidColors() {
    int[] INCORRECT_VALUES = {Integer.MIN_VALUE, -9, -2, 0, 5, 8, Integer.MAX_VALUE};
    for (int incorrect_value : INCORRECT_VALUES) {
      assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY, 10, 4 * incorrect_value, incorrect_value, 13));
    }
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidNbRounds() {
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY, 10, 0, 4, 8));
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY, 10, 33, 4, 8));
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY, 10, -5, 4, 8));
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY, 10, 13, 2, 6));
  }


  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidTimers() {
    int[] INCORRECT_VALUES = {Integer.MIN_VALUE, -9, -2, 0, 5, 61, Integer.MAX_VALUE};
    for (int incorrect_value : INCORRECT_VALUES) {
      assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY, incorrect_value, 13, 2, 6));
    }
  }

  @Test
  void test_getId() {
    final HashMap<Game, BigDecimal> TESTS = new HashMap<>() {{
      put(fixture[0], new BigDecimal(1));
      put(fixture[1], new BigDecimal(2));
      put(fixture[2], new BigDecimal(3));
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getId());
    }
  }

  @Test
  void test_getCreatedAt() {
    final HashMap<Game, Date> TESTS = new HashMap<>() {{
      put(fixture[0], new Date(2024 - 1900, 5, 6));
      put(fixture[1], new Date(2023 - 1900, 7, 9));
      put(fixture[2], new Date(2022 - 1900, 11, 12));
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getCreatedAt());
    }
  }

  @Test
  void test_getDifficulty() {
    final HashMap<Game, Game.Difficulty> TESTS = new HashMap<>() {{
      put(fixture[0], Game.Difficulty.EASY);
      put(fixture[1], Game.Difficulty.HARD);
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getDifficulty());
    }
  }

  @Test
  void test_setDifficulty() {
    Game game = fixture[0];
    assertEquals(Game.Difficulty.EASY, game.getDifficulty());

    // Change the difficulty
    game.setDifficulty(Game.Difficulty.HARD);
    assertEquals(Game.Difficulty.HARD, game.getDifficulty());
  }

  @Test
  void test_getTimer() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 50);
      put(fixture[1], 10);
      put(fixture[2], 15);
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getTimer());
    }
  }

  @Test
  void test_setTimer() {
    Game game = fixture[0];
    assertEquals(50, game.getTimer());

    // Change the timer
    game.setTimer(25);
    assertEquals(25, game.getTimer());
  }

  @Test
  void test_getNbRounds() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 17);
      put(fixture[1], 28);
      put(fixture[2], 16);
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getNbRounds());
    }
  }

  @Test
  void test_setNbRounds() {
    Game game = fixture[0];
    assertEquals(17, game.getNbRounds());

    // Change the number of rounds
    game.setNbRounds(5);
    assertEquals(5, game.getNbRounds());
  }

  @Test
  void test_getNbColors() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 3);
      put(fixture[1], 4);
      put(fixture[2], 2);
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getNbColors());
    }
  }

  @Test
  void test_setNbColors() {
    Game game = fixture[0];
    assertEquals(3, game.getNbColors());

    // Change the number of colors
    game.setNbColors(2);
    assertEquals(2, game.getNbColors());
  }

  @Test
  void test_getNbValuesPerColor() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 6);
      put(fixture[1], 13);
      put(fixture[2], 9);
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getNbValuesPerColor());
    }
  }

  @Test
  void test_setNbValuesPerColor() {
    Game game = fixture[0];
    assertEquals(6, game.getNbValuesPerColor());

    // Change the number of values per color
    game.setNbValuesPerColor(8);
    assertEquals(8, game.getNbValuesPerColor());
  }

  @Test
  void test_getPlayers() {
    final HashMap<Game, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(fixture[0], new ArrayList<>(Arrays.asList(playersFixture[0])));
      put(fixture[1], new ArrayList<>(Arrays.asList(playersFixture[1])));
      put(fixture[2], new ArrayList<>(Arrays.asList(playersFixture[2])));
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getPlayers());
    }
  }

  @Test
  void test_setPlayers() {
    Game game = fixture[0];
    assertEquals(new ArrayList<>(Arrays.asList(playersFixture[0])), game.getPlayers());

    // Add players
    game.setPlayers(new ArrayList<>(Arrays.asList(playersFixture[1])));
    assertEquals(new ArrayList<>(Arrays.asList(playersFixture[1])), game.getPlayers());
  }

  @Test
  void test_getNbPlayers() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 1);
      put(fixture[1], 2);
      put(fixture[2], 3);
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getPlayers().size());
    }
  }

  @Test
  void test_addPlayer() {
    Game game = fixture[0];
    ArrayList<Player> players = new ArrayList<>(Arrays.asList(playersFixture[0]));
    assertEquals(players.size(), game.getPlayers().size());

    Player player = new Player(new BigDecimal(2), new Game(Game.Difficulty.EASY, 20, 17, 3, 6), new User(), 10, true, 5, 5, 5);

    // Add a player
    game.addPlayer(player);
    players.add(player);
    assertEquals(players.size(), game.getPlayers().size());

    // Check if the player is the same
    assertEquals(players, game.getPlayers());
  }

  @Test
  void test_getDeck() {
    final HashMap<Game, Deck> TESTS = new HashMap<>() {{
      put(fixture[0], new Deck(3, 6));
      put(fixture[1], new Deck(4, 13));
      put(fixture[2], new Deck(2, 9));
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getDeck());
    }
  }

  @Test
  void test_sortPlayersByScore() {
    final HashMap<Game, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(fixture[0], new ArrayList<>() {{
        add(playersFixture[0][0]);
      }});
      put(fixture[1], new ArrayList<>() {{
        add(playersFixture[1][1]);
        add(playersFixture[1][0]);
      }});
      put(fixture[2], new ArrayList<>() {{
        add(playersFixture[2][1]);
        add(playersFixture[2][2]);
        add(playersFixture[2][0]);
      }});
    }};

    for (Game game : TESTS.keySet()) {
      ArrayList<Player> expected = TESTS.get(game);
      game.sortPlayersByScore();
      assertEquals(expected, game.getPlayers());
    }
  }

  @Test
  void test_toString() {
    final HashMap<Game, String> TESTS = new HashMap<>() {{
      put(fixture[0],
        "Game{id=1, createdAt=Thu Jun " + "06 00:00:00 CEST 2024, difficulty=EASY, nbRounds=17, nbColors=3, nbValuesPerColor=6}");
      put(fixture[1],
        "Game{id=2, createdAt=Wed Aug 09 00:00:00 CEST 2023, difficulty=HARD, nbRounds=28, nbColors=4, nbValuesPerColor=13}");
      put(fixture[2],
        "Game{id=3, createdAt=Mon Dec 12 00:00:00 CET 2022, difficulty=EASY, nbRounds=16, nbColors=2, nbValuesPerColor=9}");
    }};

    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.toString());
    }
  }
}

