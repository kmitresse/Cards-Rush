package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    Player[] players = new Player[]{
      new Player(new BigDecimal(1), FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE),5, true, 7,7,7),
      new Player(new BigDecimal(2), FIXTURE[1], new User("username2", "email2", "password2", new Date(1996, 4, 7), User.Gender.MALE),34, false, 7,7,7),
      new Player(new BigDecimal(3), FIXTURE[2], new User("username3", "email3", "password3", new Date(1996, 4, 7), User.Gender.MALE),22, false, 7,7,7),
      new Player(new BigDecimal(4), FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE),68, true, 7,7,7),
    };
    FIXTURE[0].addPlayer(players[0]);
    FIXTURE[0].addPlayer(players[3]);
    FIXTURE[1].addPlayer(players[1]);
    FIXTURE[2].addPlayer(players[2]);
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
      assertEquals(new BigDecimal(i + 1), FIXTURE[i].getId());
    }
  }

  @Test
  void get_Created_At() {
    Date[] dates = new Date[]{new Date(2023,12,25),
                    new Date(2024,3,26),
                    new Date(2023,9,18)};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(dates[i], FIXTURE[i].getCreatedAt());
    }
  }

  @Test
  void get_Difficulty() {
    Game.Difficulty[] difficulties = new Game.Difficulty[]{
      Game.Difficulty.EASY,
      Game.Difficulty.HARD,
      Game.Difficulty.EASY };
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(difficulties[i], FIXTURE[i].getDifficulty());
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
      assertEquals(Game.Difficulty.HARD, FIXTURE[i].getDifficulty());
      FIXTURE[i].setDifficulty(difficulties[i]);
      assertEquals(difficulties[i], FIXTURE[i].getDifficulty());
    }
  }

  @Test
  void get_nb_Rounds() {
    int[] nbRounds = new int[]{17, 52, 5};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(nbRounds[i], FIXTURE[i].getNbRounds());
    }
  }

  @Test
  void set_nb_Rounds() {
    int[] nbRounds = new int[]{17, 52, 5};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setNbRounds(10);
      assertEquals(10, FIXTURE[i].getNbRounds());
      FIXTURE[i].setNbRounds(nbRounds[i]);
      assertEquals(nbRounds[i], FIXTURE[i].getNbRounds());
    }
  }

  @Test
  void get_nb_Colors() {
    int[] nbColors = new int[]{3, 4, 2};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(nbColors[i], FIXTURE[i].getNbColors());
    }
  }

  @Test
  void set_nb_Colors() {
    int[] nbColors = new int[]{3, 4, 2};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setNbColors(1);
      assertEquals(1, FIXTURE[i].getNbColors());
      FIXTURE[i].setNbColors(nbColors[i]);
      assertEquals(nbColors[i], FIXTURE[i].getNbColors());
    }
  }

  @Test
  void getnbValuesPerColor() {
    int[] nbValuesPerColor = new int[]{6, 13, 10};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(nbValuesPerColor[i], FIXTURE[i].getNbValuesPerColor());
    }
  }

  @Test
  void setNbValuesPerColor() {
    int[] nbValuesPerColor = new int[]{6, 13, 10};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setNbValuesPerColor(8);
      assertEquals(8,FIXTURE[i].getNbValuesPerColor());
      FIXTURE[i].setNbValuesPerColor(nbValuesPerColor[i]);
      assertEquals(nbValuesPerColor[i], FIXTURE[i].getNbValuesPerColor());
    }
  }

  @Test
  void get_Players_return_rigth_number_of_players() {
    Player[] players = new Player[]{
      new Player(FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE)),
      new Player(FIXTURE[1], new User("username2", "email2", "password2", new Date(1996, 4, 7), User.Gender.MALE)),
      new Player(FIXTURE[2], new User("username3", "email3", "password3", new Date(1996, 4, 7), User.Gender.MALE)),
      new Player(FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE)),
    };
    assertEquals(2, FIXTURE[0].getPlayers().size());
    assertEquals(1, FIXTURE[1].getPlayers().size());
    assertEquals(1, FIXTURE[2].getPlayers().size());
  }

  @Test
  void get_players_return_the_right_players() {
    Player[] players = new Player[]{
      new Player(new BigDecimal(1), FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE),5, true, 7,7,7),
      new Player(new BigDecimal(2), FIXTURE[1], new User("username2", "email2", "password2", new Date(1996, 4, 7), User.Gender.MALE),34, false, 7,7,7),
      new Player(new BigDecimal(3), FIXTURE[2], new User("username3", "email3", "password3", new Date(1996, 4, 7), User.Gender.MALE),22, false, 7,7,7),
      new Player(new BigDecimal(4), FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE),68, true, 7,7,7),
    };
    assertEquals(players[0], FIXTURE[0].getPlayers().get(0));
    assertEquals(players[3], FIXTURE[0].getPlayers().get(1));
    assertEquals(players[1], FIXTURE[1].getPlayers().get(0));
    assertEquals(players[2], FIXTURE[2].getPlayers().get(0));
  }
    @Test
  void setPlayers() {
    Player[] players = new Player[]{
      new Player(new BigDecimal(5), FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE),5, true, 7,7,7),
      new Player(new BigDecimal(6), FIXTURE[1], new User("username2", "email2", "password2", new Date(1996, 4, 7), User.Gender.MALE),34, false, 7,7,7),
      new Player(new BigDecimal(7), FIXTURE[2], new User("username3", "email3", "password3", new Date(1996, 4, 7), User.Gender.MALE),22, false, 7,7,7),
      new Player(new BigDecimal(8), FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE),68, true, 7,7,7),
    };
    ArrayList<Player> newPlayers1 = new ArrayList<Player>();
    newPlayers1.add(players[0]);
    newPlayers1.add(players[3]);
    ArrayList<Player> newPlayers2 = new ArrayList<Player>();
    newPlayers2.add(players[1]);
    ArrayList<Player> newPlayers3 = new ArrayList<Player>();
    newPlayers3.add(players[2]);

    FIXTURE[0].setPlayers(newPlayers1);
    FIXTURE[1].setPlayers(newPlayers2);
    FIXTURE[2].setPlayers(newPlayers3);
    assertEquals(players[0], FIXTURE[0].getPlayers().get(0));
    assertEquals(players[3], FIXTURE[0].getPlayers().get(1));
    assertEquals(players[1], FIXTURE[1].getPlayers().get(0));
    assertEquals(players[2], FIXTURE[2].getPlayers().get(0));
  }

  @Test
  void getNbPlayers() {
    int[] nbPlayers = new int[]{2, 1, 1};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(nbPlayers[i], FIXTURE[i].getNbPlayers());
    }
  }

  @Test
  void addPlayer() {
    Player player1 = new Player(new BigDecimal(5), FIXTURE[0], new User("username5", "email5", "password5", new Date(1996, 4, 7), User.Gender.MALE),41, true, 7,7,7);
    Player player2 = new Player(new BigDecimal(6), FIXTURE[0], new User("username6", "email6", "password6", new Date(1996, 4, 7), User.Gender.MALE),41, true, 7,7,7);
    FIXTURE[0].addPlayer(player1);
    assertEquals(FIXTURE[0].getPlayers().size(), 3);
    FIXTURE[0].addPlayer(player2);
    assertEquals(FIXTURE[0].getPlayers().size(), 4);
    Player[] expected = new Player[]{
      new Player(new BigDecimal(1), FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE),5, true, 7,7,7),
      new Player(new BigDecimal(4), FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE),68, true, 7,7,7),
      player1,
      player2
    };
    assertEquals(expected[0], FIXTURE[0].getPlayers().get(0));
    assertEquals(expected[1], FIXTURE[0].getPlayers().get(1));
    assertEquals(expected[2], FIXTURE[0].getPlayers().get(2));
    assertEquals(expected[3], FIXTURE[0].getPlayers().get(3));
  }

  @Test
  void getDeck() {

  }

  @Test
  void sortPlayersByScore() {

    Player[] initial = new Player[]{
      new Player(new BigDecimal(1), FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE),5, true, 7,7,7),
      new Player(new BigDecimal(4), FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE),68, true, 7,7,7),
    };
    ArrayList<Player> players = FIXTURE[0].getPlayers();
    for (int i = 0; i < players.size(); i++) {
      assertEquals(initial[i], players.get(i));
    }
    Player[] expected = new Player[]{
      new Player(new BigDecimal(4), FIXTURE[0], new User("username4", "email4", "password4", new Date(1996, 4, 7), User.Gender.MALE),68, true, 7,7,7),
      new Player(new BigDecimal(1), FIXTURE[0], new User("username1", "email1", "password1", new Date(1996, 4, 7), User.Gender.MALE),5, true, 7,7,7),
    };
    FIXTURE[0].sortPlayersByScore();
    players = FIXTURE[0].getPlayers();
    for (int i = 0; i < players.size(); i++) {
      System.out.println(i);
      assertEquals(expected[i], players.get(i));
    }
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
