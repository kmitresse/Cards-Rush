package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


  @Test
  void test_constructor() {
    new Game();
    new Game(Game.Difficulty.EASY, 17, 3,6);
    new Game(new BigDecimal(1), new Date(2023,12,25), Game.Difficulty.EASY, 17, 3,6, new ArrayList<Player>());
    new Game(new BigDecimal(2), new Date(2024,3,26), Game.Difficulty.HARD, 52, 4,13, new ArrayList<Player>());
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidValues(){
    int[] INCORRECT_VALUES = {Integer.MIN_VALUE, -2, 0, 14, Integer.MAX_VALUE};
    for (int incorrect_value : INCORRECT_VALUES) {
      assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,4*incorrect_value,4, incorrect_value));
    }
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidColors(){
    int[] INCORRECT_VALUES = {Integer.MIN_VALUE, -9, -2, 0, 5, 8, Integer.MAX_VALUE};
    for (int incorrect_value : INCORRECT_VALUES) {
      assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,4*incorrect_value,incorrect_value, 13));
    }
  }

  @Test
  void test_constructor_throwIllegalArgumentExceptionOnInvalidNbRounds(){
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,0,4, 8));
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,33,4, 8));
    assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,-5,4, 8));
  assertThrows(IllegalArgumentException.class, () -> new Game(Game.Difficulty.EASY,13,2, 6));
  }

  @Test
  void test_getId() {
    final HashMap<Game, BigDecimal> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2023-1900,12,25), Game.Difficulty.EASY, 17, 3,6, null), new BigDecimal(1));
      put(new Game(new BigDecimal(2), new Date(2023-1900,12,25), Game.Difficulty.EASY, 17, 3,6, null), new BigDecimal(2));
      put(new Game(new BigDecimal(3), new Date(2023-1900,12,25), Game.Difficulty.EASY, 17, 3,6, null), new BigDecimal(3));
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getId());
    }
  }

  @Test
  void test_getCreatedAt() {
    final HashMap<Game, Date> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        new Date(2024-1900,5,6));
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.EASY, 17, 3,6, null),
        new Date(2023-1900,7,9));
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 17, 3,6, null),
        new Date(2022-1900,11,12));
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getCreatedAt());
    }
  }

  @Test
  void test_getDifficulty() {
    final HashMap<Game, Game.Difficulty> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        Game.Difficulty.EASY);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 17, 3,6, null),
        Game.Difficulty.HARD);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 17, 3,6, null),
        Game.Difficulty.EASY);
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getDifficulty());
    }
  }

  @Test
  void test_setDifficulty() {
    final HashMap<Game, Game.Difficulty> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        Game.Difficulty.HARD);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 17, 3,6, null),
        Game.Difficulty.HARD);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 17, 3,6, null),
        Game.Difficulty.HARD);
    }};
    for (Game game : TESTS.keySet()) {
      Game.Difficulty expected = TESTS.get(game);
      game.setDifficulty(Game.Difficulty.HARD);
      assertEquals(expected, game.getDifficulty());
    }
  }

  @Test
  void test_getNbRounds() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        17);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        28);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        16);
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getNbRounds());
    }
  }

  @Test
  void test_setNbRounds() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        5);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        5);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        5);
    }};
    for (Game game : TESTS.keySet()) {
      int expected = TESTS.get(game);
      game.setNbRounds(5);
      assertEquals(expected, game.getNbRounds());
    }
  }

  @Test
  void test_getNbColors() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        3);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        4);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        2);
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getNbColors());
    }
  }

  @Test
  void test_setNbColors() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        4);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        4);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        4);
    }};
    for (Game game : TESTS.keySet()) {
      int expected = TESTS.get(game);
      game.setNbColors(4);
      assertEquals(expected, game.getNbColors());
    }
  }

  @Test
  void test_getNbValuesPerColor() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        6);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        13);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        9);
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getNbValuesPerColor());
    }
  }

  @Test
  void test_setNbValuesPerColor() {
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        12);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        12);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        12);
    }};
    for (Game game : TESTS.keySet()) {
      int expected = TESTS.get(game);
      game.setNbValuesPerColor(12);
      assertEquals(expected, game.getNbValuesPerColor());
    }
  }

  @Test
  void test_getPlayers() {
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5, 5, 5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5, 5, 5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, true, 5, 5, 5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5, 5, 5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 5, 5, 5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5, 5, 5));
    final HashMap<Game, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024 - 1900, 5, 6), Game.Difficulty.EASY, 17, 3, 6, players1),
        players1);
      put(new Game(new BigDecimal(2), new Date(2023 - 1900, 7, 9), Game.Difficulty.HARD, 28, 4, 13, players2),
        players2);
      put(new Game(new BigDecimal(3), new Date(2022 - 1900, 11, 12), Game.Difficulty.EASY, 16, 2, 9, players3),
        players3);
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getPlayers());
    }
  }

  @Test
  void test_setPlayers() {
    ArrayList<Player> players = new ArrayList<>();
    players.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players.add(new Player(new BigDecimal(3), new Game(), new User(), 10, true, 5,5,5));
    players.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5));
    players.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 5,5,5));
    players.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5,5,5));
    final HashMap<Game, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        players);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        players);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        players);
    }};
    for (Game game : TESTS.keySet()) {
      ArrayList<Player> expected = TESTS.get(game);
      game.setPlayers(players);
      assertEquals(expected, game.getPlayers());
    }
  }

  @Test
  void test_getNbPlayers() {
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5,5,5));
    final HashMap<Game, Integer> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, players1),
        1);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, players2),
        2);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, players3),
        3);
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getPlayers().size());
    }
  }

  @Test
  void test_addPlayer() {
    //Initial arrays
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();

    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(3), new Game(), new User(), 10, true, 5,5,5));

    //New Player
    Player player = new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5);

    //Expected arrays
    ArrayList<Player> players1Expected = new ArrayList<>();
    ArrayList<Player> players2Expected = new ArrayList<>();
    ArrayList<Player> players3Expected = new ArrayList<>();
    players1Expected.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players1Expected.add(player);
    players2Expected.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players2Expected.add(player);
    players3Expected.add(new Player(new BigDecimal(3), new Game(), new User(), 10, true, 5,5,5));
    players3Expected.add(player);
    final HashMap<Game, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, players1),
        players1Expected);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, players2),
        players2Expected);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, players3),
        players3Expected);
    }};
    for (Game game : TESTS.keySet()) {
      ArrayList<Player> expected = TESTS.get(game);
      game.addPlayer(player);
      assertEquals(expected, game.getPlayers());
    }
  }

  @Test
  void getDeck() {
    final HashMap<Game, Deck> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        new Deck(3,6));
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        new Deck(4,13));
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        new Deck(2,9));
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.getDeck());
    }
  }

  @Test
  void sortPlayersByScore() {
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 15, true, 5,5,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 20, true, 5,5,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 6, true, 5,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 13, true, 5,5,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 9, true, 5,5,5));


    //Expected arrays
    ArrayList<Player> players1Expected = new ArrayList<>();
    ArrayList<Player> players2Expected = new ArrayList<>();
    ArrayList<Player> players3Expected = new ArrayList<>();
    players1Expected.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players2Expected.add(new Player(new BigDecimal(3), new Game(), new User(), 20, true, 5,5,5));
    players2Expected.add(new Player(new BigDecimal(2), new Game(), new User(), 15, true, 5,5,5));
    players3Expected.add(new Player(new BigDecimal(6), new Game(), new User(), 13, true, 5,5,5));
    players3Expected.add(new Player(new BigDecimal(5), new Game(), new User(), 9, true, 5,5,5));
    players3Expected.add(new Player(new BigDecimal(4), new Game(), new User(), 6, true, 5,5,5));

    final HashMap<Game, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, players1),
        players1Expected);
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, players2),
        players2Expected);
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, players3),
        players3Expected);
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
      put(new Game(new BigDecimal(1), new Date(2024-1900,5,6), Game.Difficulty.EASY, 17, 3,6, null),
        "Game{id=1, createdAt=Thu Jun 06 00:00:00 CEST 2024, difficulty=EASY, nbRounds=17, nbColors=3, nbValuesPerColor=6}");
      put(new Game(new BigDecimal(2), new Date(2023-1900,7,9), Game.Difficulty.HARD, 28, 4,13, null),
        "Game{id=2, createdAt=Wed Aug 09 00:00:00 CEST 2023, difficulty=HARD, nbRounds=28, nbColors=4, nbValuesPerColor=13}");
      put(new Game(new BigDecimal(3), new Date(2022-1900,11,12), Game.Difficulty.EASY, 16, 2,9, null),
        "Game{id=3, createdAt=Mon Dec 12 00:00:00 CET 2022, difficulty=EASY, nbRounds=16, nbColors=2, nbValuesPerColor=9}");
    }};
    for (Game game : TESTS.keySet()) {
      assertEquals(TESTS.get(game), game.toString());
    }
  }
}
