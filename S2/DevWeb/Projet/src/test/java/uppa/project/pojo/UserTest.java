package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  User[] fixture;
  static Player[][] playersFixture;

  @BeforeAll
  static void beforeAll() {

    playersFixture = new Player[][]{
      {
        new Player(new BigDecimal(1),
          new Game(Game.Difficulty.EASY, 50,20, 4, 13),
          new User(), 10, false, 10, 9, 5)
      },
      {
        new Player(new BigDecimal(2),
          new Game(Game.Difficulty.EASY, 50, 20, 4, 13),
          new User(), 10, true, 20, 17, 12),
        new Player(new BigDecimal(3),
          new Game(Game.Difficulty.EASY, 10, 20, 4, 13),
          new User(), 10, false, 15, 5, 2)
      },
      {
        new Player(new BigDecimal(4),
          new Game(Game.Difficulty.EASY, 50, 20, 4, 13),
          new User(), 10, true, 5, 3, 1),
        new Player(new BigDecimal(5),
          new Game(Game.Difficulty.EASY, 10, 20, 4, 13),
          new User(), 10, true, 16, 16, 10),
        new Player(new BigDecimal(6),
          new Game(Game.Difficulty.EASY, 15, 20, 4, 13),
          new User(), 10, true, 17, 11, 4)
      }
    };
  }
  @BeforeEach
  void beforeEach() {
    fixture = new User[]{
      new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(1996 - 1900, Calendar.FEBRUARY, 20), User.Gender.MALE, new ArrayList<>(Arrays.asList(playersFixture[0]))),
      new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(1998 - 1900, Calendar.JUNE, 6), User.Gender.FEMALE, new ArrayList<>(Arrays.asList(playersFixture[1]))),
      new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(1996 - 1900, Calendar.SEPTEMBER, 18), User.Gender.OTHER, new ArrayList<>(Arrays.asList(playersFixture[2]))),
    };
  }

  @Test
  void test_constructor() {
    new User();
    new User("username", "email", "password", new Date(), User.Gender.OTHER);
    new User(new BigDecimal(1), "username1", "email1", "password1", new Date(), User.Gender.MALE, null);
    new User(new BigDecimal(2), "username1", "email1", "password1", new Date(), User.Gender.MALE, new ArrayList<Player>());
  }

    @Test
  void test_getId() {
    final HashMap<User, BigDecimal> TESTS = new HashMap<>() {{
      put(fixture[0], new BigDecimal(1));
      put(fixture[1], new BigDecimal(2));
      put(fixture[2], new BigDecimal(3));
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getId());
    }
  }

  @Test
  void test_getUsername() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(fixture[0], "username1");
      put(fixture[1], "username2");
      put(fixture[2], "username3");
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getUsername());
    }
  }

  @Test
  void test_setUsername() {
    User user = fixture[0];
    assertEquals("username1", user.getUsername());

    // Change the username
    user.setUsername("new-username");
    assertEquals("new-username", user.getUsername());
  }

  @Test
  void test_getEmail() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
    put(fixture[0], "email1");
    put(fixture[1], "email2");
    put(fixture[2], "email3");
  }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getEmail());
    }
  }

  @Test
  void test_setEmail() {
    User user = fixture[0];
    assertEquals("email1", user.getEmail());

    // Change the email
    user.setEmail("new-email");
    assertEquals("new-email", user.getEmail());
  }

  @Test
  void test_getPassword() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(fixture[0], "password1");
      put(fixture[1], "password2");
      put(fixture[2], "password3");
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getPassword());
    }
  }


  @Test
  void test_setPassword() {
    User user = fixture[0];
    assertEquals("password1", user.getPassword());

    // Change the password
    user.setPassword("new-password");
    assertEquals(User.hashPassword("new-password"), user.getPassword());
  }

  @Test
  void test_getBirth() {
    final HashMap<User, Date> TESTS = new HashMap<>() {{
      put(fixture[0], new Date(1996 - 1900, Calendar.FEBRUARY, 20));
      put(fixture[1], new Date(1998 - 1900, Calendar.JUNE, 6));
      put(fixture[2], new Date(1996 - 1900, Calendar.SEPTEMBER, 18));
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getBirth());
    }
  }


  @Test
  void test_setBirth() {
    User user = fixture[0];
    assertEquals(new Date(1996 - 1900, Calendar.FEBRUARY, 20), user.getBirth());

    // Change the birth
    user.setBirth(new Date(1996 - 1900, Calendar.MARCH, 20));
    assertEquals(new Date(1996 - 1900, Calendar.MARCH, 20), user.getBirth());
  }

  @Test
  void test_getGender() {
    final HashMap<User, User.Gender> TESTS = new HashMap<>() {{
      put(fixture[0], User.Gender.MALE);
      put(fixture[1], User.Gender.FEMALE);
      put(fixture[2], User.Gender.OTHER);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getGender());
    }
  }

  @Test
  void test_setGender() {
    User user = fixture[0];
    assertEquals(User.Gender.MALE, user.getGender());

    // Change the gender
    user.setGender(User.Gender.FEMALE);
    assertEquals(User.Gender.FEMALE, user.getGender());
  }

  @Test
  void test_UserGender() {

    // Expect the right number of genders
    assertEquals(3, User.Gender.values().length);

    // Expect the right name and ordinal for each gender
    final String[] EXPECTED_NAMES = new String[]{
      "MALE", "FEMALE", "OTHER"
    };

    for (int index = 0; index < EXPECTED_NAMES.length; index++) {
      String expectedName = EXPECTED_NAMES[index];
      User.Gender currentValue = User.Gender.values()[index];

      assertEquals(index, currentValue.ordinal());     // Ordinal
      assertEquals(expectedName, currentValue.name()); // Name
    }
  }

  @Test
  void test_verifyPassword() {
    String [] passwords = {"password1", "password2", "password3"};
    User[] users = {
      new User("username1", "email1", "password1", new Date(), User.Gender.MALE),
      new User("username2", "email2", "password2", new Date(), User.Gender.MALE),
      new User("username3", "email3", "password3", new Date(), User.Gender.MALE)
    };
    for (int index = 0 ; index< users.length; index++) {
      assertTrue(users[index].verifyPassword(passwords[index]));
    }
  }

  @Test
  void test_getPlayedGames() {
    final HashMap<User, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(fixture[0], new ArrayList<>(Arrays.asList(playersFixture[0])));
      put(fixture[1], new ArrayList<>(Arrays.asList(playersFixture[1])));
      put(fixture[2], new ArrayList<>(Arrays.asList(playersFixture[2])));
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getPlayedGames());
    }

  }

  @Test
  void test_addPlayedGame() {
    User user = fixture[0];
    Player player = new Player(new BigDecimal(4),
      new Game(Game.Difficulty.EASY, 10,20, 4, 13),
      new User(), 10, true, 5, 3, 1);
    user.addPlayedGame(player);
    ArrayList<Player> expected = new ArrayList<>(Arrays.asList(playersFixture[0]));
    expected.add(player);
    assertEquals(expected, user.getPlayedGames());
  }

  @Test
  void test_getNbPlayedGame() {
    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 1);
      put(fixture[1], 2);
      put(fixture[2], 3);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getPlayedGames().size());
    }
  }

  @Test
  void test_getNbWin() {
    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 0);
      put(fixture[1], 1);
      put(fixture[2], 3);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbWin());
    }
  }

  @Test
  void test_getWinRate() {

    final HashMap<User, Double> TESTS = new HashMap<>() {{
      put(fixture[0], 0.0);
      put(fixture[1], 50.);
      put(fixture[2], 100.);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getWinRate());
    }
  }

  @Test
  void test_getNbClicks() {
    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 10);
      put(fixture[1], 35);
      put(fixture[2], 38);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbClicks());
    }
  }

  @Test
  void test_getNbRightClicks() {
    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 9);
      put(fixture[1], 22);
      put(fixture[2], 30);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbRightClicks());
    }
  }

  @Test
  void test_getRightClickPercentRate() {
    final HashMap<User, Double> TESTS = new HashMap<>() {{
      put(fixture[0], 90.);
      put(fixture[1], 62.85);
      put(fixture[2], 78.94);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getRightClickPercentRate());
    }
  }

  @Test
  void test_getNbRapidClicks() {
    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(fixture[0], 5);
      put(fixture[1], 14);
      put(fixture[2], 15);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbRapidClicks());
    }
  }

  @Test
  void test_getRapidClickPercentRate() {
    final HashMap<User, Double> TESTS = new HashMap<>() {{
      put(fixture[0], 50.);
      put(fixture[1], 40.);
      put(fixture[2], 39.47);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getRapidClickPercentRate());
    }
  }

  @Test
  void test_toString() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(1996-1900, 1, 4), User.Gender.MALE,null),
        "User{id='1', username=username1, email=email1, birth='Sun Feb 04 00:00:00 CET 1996', gender='MALE'}"
        );
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(1996-1900, 2, 6), User.Gender.FEMALE,null),
        "User{id='2', username=username2, email=email2, birth='Wed Mar 06 00:00:00 CET 1996', gender='FEMALE'}");
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(1996-1900, 3, 5), User.Gender.OTHER,null),
        "User{id='3', username=username3, email=email3, birth='Fri Apr 05 00:00:00 CEST 1996', gender='OTHER'}");
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.toString());
    }
  }
}
