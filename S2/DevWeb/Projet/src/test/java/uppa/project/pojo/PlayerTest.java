package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


  @Test
  void test_constructor() {
    new Player();
    new Player(new Game(Game.Difficulty.EASY,4,2,3), new User());
    new Player(new Game(Game.Difficulty.EASY,4,2,3), new User());
    new Player(new Game(Game.Difficulty.EASY,4,2,3), new User());
    new Player(new BigDecimal(1), new Game(), new User(),45, true, 15, 20, 6);
  }

  @Test
  void get_game() {
    Game[] games = new Game[]{
      new Game(new BigDecimal(1), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(2), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(3), new Date(), Game.Difficulty.EASY,20,4,5,null),
    };

    final HashMap<Player, Game> TESTS = new HashMap<>() {{
      put(new Player(games[0], new User()), games[0]);
      put(new Player(games[1], new User()), games[1]);
      put(new Player(games[2], new User()), games[2]);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getGame());
    }
  }

  @Test
  void set_game() {
    Game[] games = new Game[]{
      new Game(new BigDecimal(1), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(2), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(3), new Date(), Game.Difficulty.EASY,20,4,5,null),
    };
    Game game =
      new Game(new BigDecimal(4), new Date(), Game.Difficulty.HARD,20,4,5,null);

    final HashMap<Player, Game> TESTS = new HashMap<>() {{
      put(new Player(games[0], new User()), game);
      put(new Player(games[1], new User()), game);
      put(new Player(games[2], new User()), game);
    }};
    for (Player player : TESTS.keySet()) {
      Game expected = TESTS.get(player);
      player.setGame(game);
      assertEquals(expected, player.getGame());
    }
  }

  @Test
  void get_user() {
    Game[] games = new Game[]{
      new Game(new BigDecimal(1), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(2), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(3), new Date(), Game.Difficulty.EASY,20,4,5,null),
    };
    User[] users = new User[]{
      new User("username1", "email1", "password1", new Date(), User.Gender.MALE),
      new User("username2", "email2", "password2", new Date(), User.Gender.FEMALE),
      new User("username3", "email3", "password3", new Date(), User.Gender.OTHER),
    };

    final HashMap<Player, User> TESTS = new HashMap<>() {{
      put(new Player(games[0], users[0]), users[0]);
      put(new Player(games[1], users[1]), users[1]);
      put(new Player(games[2], users[2]), users[2]);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getUser());
    }
  }


  @Test
  void set_user() {
    Game[] games = new Game[]{
      new Game(new BigDecimal(1), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(2), new Date(), Game.Difficulty.EASY,20,4,5,null),
      new Game(new BigDecimal(3), new Date(), Game.Difficulty.EASY,20,4,5,null),
    };
    User[] users = new User[]{
      new User("username1", "email1", "password1", new Date(), User.Gender.MALE),
      new User("username2", "email2", "password2", new Date(), User.Gender.FEMALE),
      new User("username3", "email3", "password3", new Date(), User.Gender.OTHER),
    };
    User user =   new User("username000", "email000", "password000", new Date(), User.Gender.OTHER);

    final HashMap<Player, User> TESTS = new HashMap<>() {{
      put(new Player(games[0], users[0]), user);
      put(new Player(games[1], users[1]), user);
      put(new Player(games[2], users[2]), user);
    }};
    for (Player player : TESTS.keySet()) {
      User expected =TESTS.get(player);
      player.setUser(user);
      assertEquals(expected, player.getUser());
    }
  }

  @Test
  void get_score() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5),10);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, true, 5,5,5),20);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 5,5,5),15);;
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getScore());
    }
  }

  @Test
  void set_score() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5),50);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, true, 5,5,5),50);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 5,5,5),50);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.setScore(50);
      assertEquals(expected, player.getScore());
    }
  }

  @Test
  void update_score() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5),13);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, true, 5,5,5),23);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 5,5,5),18);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.updateScore(3);
      assertEquals(expected, player.getScore());
      expected -= 5;
      player.updateScore(-5);
      assertEquals(expected, player.getScore());
    }
  }

  @Test
  void is_winner() {
    final HashMap<Player, Boolean> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5),true);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false, 5,5,5),false);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 5,5,5),true);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.isWinner());
    }
  }

  @Test
  void set_winner() {
    Player[] players = new Player[]{
      new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,5,5),
      new Player(new BigDecimal(2), new Game(), new User(), 20, false, 5,5,5),
      new Player(new BigDecimal(3), new Game(), new User(), 15, false, 5,5,5),
    };
    for (Player player: players){
      player.setWinner();
      assertTrue(player.isWinner());
    }
  }

  @Test
  void get_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),6);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,4,1),4);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 2,5,5),2);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getClickCount());
    }
  }

  @Test
  void set_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),3);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,4,1),3);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 2,5,5),3);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.setClickCount(3);
      assertEquals(expected, player.getClickCount());
    }
  }

  @Test
  void increment_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),7);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,4,1),5);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 2,5,5),3);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.incrementClickCount();
      assertEquals(expected, player.getClickCount());
    }
  }

  @Test
  void get_right_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),5);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,4,1),4);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 2,3,5),3);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRightClickCount());
    }
  }

  @Test
  void set_right_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),3);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,4,1),3);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 2,5,5),3);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.setRightClickCount(3);
      assertEquals(expected, player.getRightClickCount());
    }
  }

  @Test
  void increment_right_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),6);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,8 ,4,1),5);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 9,5,5),6);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.incrementRightClickCount();
      assertEquals(expected, player.getRightClickCount());
    }
  }

  @Test
  void get_ratio_right_click() {
    final HashMap<Player, Double> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,3,5),50.);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,1,1),25.);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 17,5,5),29.41);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRatioRightClick());
    }

  }

  @Test
  void get_rapid_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,3,5),5);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,1,1),1);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 17,3,2),2);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRapidClickCount());
    }
  }

  @Test
  void set_rapid_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),3);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,4,1),3);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 2,5,5),3);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.setRightClickCount(3);
      assertEquals(expected, player.getRightClickCount());
    }

  }

  @Test
  void increment_rapid_click_count() {
    final HashMap<Player, Integer> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,3,5),6);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,1,1),2);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 17,3,2),3);
    }};
    for (Player player : TESTS.keySet()) {
      int expected = TESTS.get(player);
      player.incrementRapidClickCount();
      assertEquals(expected, player.getRapidClickCount());
    }
  }

  @Test
  void get_ratio_rapid_click() {
    final HashMap<Player, Double> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,3,5),83.33);
      put(new Player(new BigDecimal(2), new Game(), new User(), 20, false,4 ,1,1),25.);
      put(new Player(new BigDecimal(3), new Game(), new User(), 15, true, 17,3,2),11.76);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getRatioRapidClick());
    }
  }

  @Test
  void get_deck() {
    Deck[] decks = new Deck[]{
      new Deck(2,10),
      new Deck(4,13),
      new Deck(3,6),
    };

    Game[] games = new Game[]{
      new Game(new BigDecimal(1), new Date(), Game.Difficulty.EASY,20,2,10,null),
      new Game(new BigDecimal(2), new Date(), Game.Difficulty.EASY,52,4,13,null),
      new Game(new BigDecimal(3), new Date(), Game.Difficulty.EASY,18,3,6,null),
    };
    final HashMap<Player, Deck> TESTS = new HashMap<>() {{
      put(new Player(games[0], new User()), decks[0]);
      put(new Player(games[1], new User()), decks[1]);
      put(new Player(games[2], new User()), decks[2]);
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.getDeck());
    }
  }

  @Test
  void test_to_string() {
    Game game = new Game(new BigDecimal(1), new Date(2024-1900,3,27), Game.Difficulty.EASY,20,4,5,null);
    User user = new User("username1", "email1", "password1", new Date(1996-1900,2,20), User.Gender.MALE);
    final HashMap<Player, String> TESTS = new HashMap<>() {{
      put(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 6,5,5),
        "Player{id=1, game=Game{id=null, createdAt=null, difficulty=null, nbRounds=0, nbColors=0, nbValuesPerColor=0}, " +
        "user=User{id='null', username=null, email=null, birth='null', gender='null'}, " +
        "score=10, winner=true, clickCount=6, rightClickCount=5, rapidClickCount=5}");
      put(new Player(new BigDecimal(2), game, new User(), 20, false, 4,2,1),
        "Player{id=2, game=Game{id=1, createdAt=Sat Apr 27 00:00:00 CEST 2024, difficulty=EASY, nbRounds=20, nbColors=4, nbValuesPerColor=5}, " +
          "user=User{id='null', username=null, email=null, birth='null', gender='null'}, " +
          "score=20, winner=false, clickCount=4, rightClickCount=2, rapidClickCount=1}");
      put(new Player(new BigDecimal(3), new Game(), user, 15, true, 10,5,5),
        "Player{id=3, game=Game{id=null, createdAt=null, difficulty=null, nbRounds=0, nbColors=0, nbValuesPerColor=0}, " +
          "user=User{id='null', username=username1, email=email1, birth='Wed Mar 20 00:00:00 CET 1996', gender='MALE'}, " +
          "score=15, winner=true, clickCount=10, rightClickCount=5, rapidClickCount=5}");
    }};
    for (Player player : TESTS.keySet()) {
      assertEquals(TESTS.get(player), player.toString());
    }
  }
}
