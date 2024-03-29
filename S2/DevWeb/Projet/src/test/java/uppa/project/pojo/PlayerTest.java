package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  static Player[] fixture;
  static Game[] games;
  static User[] users;

  @BeforeAll
  static void beforeAll() {
    games = new Game[]{
      new Game(new BigDecimal(1), new Date(101, 2, 4), Game.Difficulty.EASY, 50,18,2,10,null),
      new Game(new BigDecimal(2), new Date(101, 2, 4), Game.Difficulty.EASY, 10,18,4,13,null),
      new Game(new BigDecimal(3), new Date(101, 2, 4), Game.Difficulty.EASY, 20,18,3,6,null),
      new Game(new BigDecimal(4), new Date(101, 2, 4), Game.Difficulty.HARD,25,18,3,6,null),
    };

    users = new User[]{
      new User("username1", "email1", "password1", new Date(), User.Gender.MALE),
      new User("username2", "email2", "password2", new Date(), User.Gender.FEMALE),
      new User("username3", "email3", "password3", new Date(), User.Gender.OTHER),
      new User("username4", "email4", "password4", new Date(100, 1, 1), User.Gender.OTHER)

    };
  }
  @BeforeEach
  void beforeEach() {
    fixture = new Player[]{
      new Player(
        new BigDecimal(1),
        games[0],
        users[0],
        10, true, 6,5,5
      ),
      new Player(
        new BigDecimal(2),
        games[1],
        users[1],
        20, false, 4,2,1
      ),
      new Player(
        new BigDecimal(3),
        games[2],
        users[2],
        15, true, 10,5,5
      ),
      new Player(
        new BigDecimal(4),
        games[3],
        users[3],
        15, true, 10,5,5
      )
    };

  }

  @Test
  void test_constructor() {
    new Player();
    new Player(new Game(Game.Difficulty.EASY,25,4,2,3), new User());
    new Player(new Game(Game.Difficulty.EASY,10,4,2,3), new User());
    new Player(new Game(Game.Difficulty.EASY,20,4,2,3), new User());
  }

  @Test
  void test_getGame() {
    final HashMap<Player, Game> TESTS = new HashMap<>() {{
      put(fixture[0], games[0]);
      put(fixture[1], games[1]);
      put(fixture[2], games[2]);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getGame());
    }
  }

  @Test
  void test_setGame() {
    Player player = fixture[0];
    Game game = player.getGame();

    // Set a new game
    Game newGame = new Game(new BigDecimal(4), new Date(), Game.Difficulty.HARD,10,20,4,5,null);
    player.setGame(newGame);

    assertNotEquals(game, player.getGame());
    assertEquals(newGame, player.getGame());
  }

  @Test
  void test_getUser() {
    Player player = fixture[0];

    User expected = new User("username1", "email1", "password1", new Date(), User.Gender.MALE);
    assertEquals(expected.getUsername(), player.getUser().getUsername());
    assertEquals(expected.getEmail(), player.getUser().getEmail());
    assertEquals(expected.getPassword(), player.getUser().getPassword());
    assertEquals(expected.getGender(), player.getUser().getGender());
  }


  @Test
  void test_setUser() {
    Player player = fixture[0];
    User user = player.getUser();

    // Set a new user
    User newUser = new User("username2", "email2", "password2", new Date(), User.Gender.FEMALE);
    player.setUser(newUser);

    assertNotEquals(user, player.getUser());
    assertEquals(newUser, player.getUser());
  }

  @Test
  void test_getScore() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(fixture[0],10);
      put(fixture[1],20);
      put(fixture[2],15);;
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getScore());
    }
  }

  @Test
  void test_setScore() {
    Player player = fixture[0];
    int score = player.getScore();

    // Set a new score
    player.setScore(15);
    assertEquals(15, player.getScore());
    assertNotEquals(score, player.getScore());
  }

  @Test
  void test_addToScore() {
    Player player = fixture[0];
    int score = player.getScore();

    // Update the score
    player.addToScore(5);
    assertEquals(score + 5, player.getScore());
    assertNotEquals(score, player.getScore());
  }

  @Test
  void test_isWinner() {
    final HashMap<Player, Boolean> TESTS = new HashMap<>() {{
      put(fixture[0], true);
      put(fixture[1], false);
      put(fixture[2], true);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.isWinner());
    }
  }

  @Test
  void test_setWinner() {
    Player player = fixture[1];
    assertFalse(player.isWinner());

    player.setWinner();
    assertTrue(player.isWinner());
  }

  @Test
  void test_getClickCount() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 6);
      put(fixture[1], 4);
      put(fixture[2], 10);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getClickCount());
    }
  }

  @Test
  void test_setClickCount() {
    Player player = fixture[0];
    int clickCount = player.getClickCount();

    // Set a new click count
    player.setClickCount(3);
    assertEquals(3, player.getClickCount());
    assertNotEquals(clickCount, player.getClickCount());
  }

  @Test
  void test_incrementClickCount() {
    Player player = fixture[0];
    int clickCount = player.getClickCount();

    // Increment the click count
    player.incrementClickCount();
    assertEquals(clickCount + 1, player.getClickCount());
    assertNotEquals(clickCount, player.getClickCount());
  }

  @Test
  void test_getRightClickCount() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 5);
      put(fixture[1], 2);
      put(fixture[2], 5);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRightClickCount());
    }
  }

  @Test
  void test_setRightClickCount() {
    Player player = fixture[0];
    int rightClickCount = player.getRightClickCount();

    // Set a new right click count
    player.setRightClickCount(3);
    assertEquals(3, player.getRightClickCount());
    assertNotEquals(rightClickCount, player.getRightClickCount());
  }

  @Test
  void test_incrementRightClickCount() {
    Player player = fixture[0];
    int rightClickCount = player.getRightClickCount();

    // Increment the right click count
    player.incrementRightClickCount();
    assertEquals(rightClickCount + 1, player.getRightClickCount());
    assertNotEquals(rightClickCount, player.getRightClickCount());
  }

  @Test
  void test_getRatioRightClick() {
    final HashMap<Player, Double> TESTS = new HashMap<>() {{
      put(fixture[0], 83.33);
      put(fixture[1], 50.);
      put(fixture[2], 50.);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRatioRightClick());
    }

  }

  @Test
  void test_getRapidClickCount() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 5);
      put(fixture[1], 1);
      put(fixture[2], 5);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRapidClickCount());
    }
  }

  @Test
  void test_setRapidClickCount() {
    Player player = fixture[0];
    int rapidClickCount = player.getRapidClickCount();

    // Set a new rapid click count
    player.setRapidClickCount(3);
    assertEquals(3, player.getRapidClickCount());
    assertNotEquals(rapidClickCount, player.getRapidClickCount());
  }

  @Test
  void test_incrementRapidClickCount() {
    Player player = fixture[0];
    int rapidClickCount = player.getRapidClickCount();

    // Increment the rapid click count
    player.incrementRapidClickCount();
    assertEquals(rapidClickCount + 1, player.getRapidClickCount());
    assertNotEquals(rapidClickCount, player.getRapidClickCount());
  }

  @Test
  void test_getRatioRapidClick() {
    final HashMap<Player, Double> TESTS = new HashMap<>() {{
      put(fixture[0], 83.33);
      put(fixture[1], 25.);
      put(fixture[2], 50.);
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRatioRapidClick());
    }
  }

  @Test
  void test_getDeck() {
    final HashMap<Player, Deck> TESTS = new HashMap<>() {{
      put(fixture[0], new Deck(2,10));
      put(fixture[1], new Deck(4,13));
      put(fixture[2], new Deck(3,6));
    }};

    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getDeck());
    }
  }

  @Test
  void test_toString() {
    String expected = "Player{id=4, game=Game{id=4, createdAt=Sun Mar 04 00:00:00 CET 2001, difficulty=HARD, nbRounds=18, nbColors=3, nbValuesPerColor=6}, user=User{id='null', username=username4, email=email4, birth='Tue Feb 01 00:00:00 CET 2000', gender='OTHER'}, score=15, winner=true, clickCount=10, rightClickCount=5, rapidClickCount=5}";

    assertEquals(expected, fixture[3].toString());
  }
}
