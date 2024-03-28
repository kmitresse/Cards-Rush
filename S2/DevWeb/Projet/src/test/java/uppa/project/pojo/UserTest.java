package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

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
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(1996-1900,2,6), User.Gender.MALE,null), new BigDecimal(1));
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(1996-1900, 2, 6), User.Gender.MALE,null), new BigDecimal(2));
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(1996-1900, 3, 5), User.Gender.MALE,null), new BigDecimal(3));
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getId());
    }
  }

  @Test
  void test_getUsername() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(1996-1900, 1, 4), User.Gender.MALE,null), "username1");
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(1996-1900, 2, 6), User.Gender.MALE,null), "username2");
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(1996-1900, 3, 5), User.Gender.MALE,null), "username3");
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getUsername());
    }
  }

  @Test
  void test_setUsername() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE,null), "username");
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.MALE,null), "username");
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.MALE,null), "username");
    }};
    for (User user : TESTS.keySet()) {
      String expected = TESTS.get(user);
      user.setUsername("username");
      assertEquals(expected, user.getUsername());
    }
  }

  @Test
  void test_getEmail() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
    put(new User(new BigDecimal(1), "username1", "email1", "password1",
      new Date(), User.Gender.MALE,null), "email1");
    put(new User(new BigDecimal(2), "username2", "email2", "password2",
      new Date(), User.Gender.MALE,null), "email2");
    put(new User(new BigDecimal(3), "username3", "email3", "password3",
      new Date(), User.Gender.MALE,null), "email3");
  }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getEmail());
    }
  }

  @Test
  void test_setEmail() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE,null), "email");
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.MALE,null), "email");
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.MALE,null), "email");
    }};
    for (User user : TESTS.keySet()) {
      String expected = TESTS.get(user);
      user.setEmail("email");
      assertEquals(expected, user.getEmail());
    }
  }

  @Test
  void test_getPassword() {
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE,null), "password1");
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.MALE,null), "password2");
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.MALE,null), "password3");
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getPassword());
    }
  }


  @Test
  void test_setPassword() {
    String newPassword = User.hashPassword("password");
    System.out.println(newPassword);
    final HashMap<User, String> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE,null), newPassword);
        put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.MALE,null), newPassword);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.MALE,null), newPassword);
    }};
    for (User user : TESTS.keySet()) {
      String expected = TESTS.get(user);
      user.setPassword("password");
      assertEquals(expected, user.getPassword());
    }
  }

  @Test
  void test_getBirth() {
    final HashMap<User, Date> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(2000-1900, 5,1), User.Gender.MALE,null), new Date(2000-1900, 5,1));
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(2023-1900, 12,25), User.Gender.MALE,null),new Date(2023-1900, 12,25));
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(2000-1900, 3,03), User.Gender.MALE,null), new Date(2000-1900, 3,03));
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getBirth());
    }
  }

  @Test
  void setBirth() {
    //TODO: DELETE
    final HashMap<User, Date> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(2000-1900, 5,1), User.Gender.MALE,null), new Date(2000-1900, 1,1));
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(2023-1900, 12,25), User.Gender.MALE,null),new Date(2000-1900, 1,1));
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(2000-1900, 3,03), User.Gender.MALE,null), new Date(2000-1900, 1,1));
    }};
    for (User user : TESTS.keySet()) {
      Date expected = TESTS.get(user);
      user.setBirth(new Date(2000-1900, 1,1));
      assertEquals(expected, user.getBirth());
    }
  }

  @Test
  void getGender() {
    final HashMap<User, User.Gender> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE,null), User.Gender.MALE);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,null), User.Gender.FEMALE);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,null), User.Gender.OTHER);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getGender());
    }
  }

  @Test
  void test_setGender() {
    final HashMap<User, User.Gender> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE,null), User.Gender.OTHER);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,null), User.Gender.OTHER);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,null), User.Gender.OTHER);
    }};
    for (User user : TESTS.keySet()) {
      User.Gender expected = TESTS.get(user);
      user.setGender(User.Gender.OTHER);
      assertEquals(expected, user.getGender());
    }
  }

  @Test
  void verifyPassword() {
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
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5,5,5));

    final HashMap<User, ArrayList<Player>> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), players1);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), players2);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), players3);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getPlayedGames());
    }

  }

  @Test
  void test_getNbPlayedGame() {
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player());
    players2.add(new Player());
    players2.add(new Player());
    players3.add(new Player());
    players3.add(new Player());
    players3.add(new Player());
    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 1);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 2);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 3);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getPlayedGames().size());
    }
  }

  @Test
  void test_getNbWin() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 5,5,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5,5,5));

    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 0);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 1);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 3);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbWin());
    }
  }

  @Test
  void test_getWinRate() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    ArrayList<Player> players4 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 5,5,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 5,5,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 5,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5,5,5));
    players4.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 5,5,5));
    players4.add(new Player(new BigDecimal(5), new Game(), new User(), 10, false, 5,5,5));
    players4.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 5,5,5));

    final HashMap<User, Double> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 0.);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE, players2), 50.);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER, players3), 100.);
      put(new User(new BigDecimal(4), "username4", "email4", "password4",
        new Date(), User.Gender.OTHER, players4), 66.66);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getWinRate());
    }
  }

  @Test
  void test_getNbClicks() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,5,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 6,5,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 7,5,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 8,5,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 9,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 10,5,5));

    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 5);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 6+7);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 8+9+10);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbClicks());
    }
  }

  @Test
  void test_getNbRightClicks() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,1,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 6,2,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 7,3,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 8,4,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 9,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 10,6,5));

    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 1);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 5);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 15);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbRightClicks());
    }
  }

  @Test
  void test_getRightClickPercentRate() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,1,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 6,2,5));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 7,3,5));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 8,4,5));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 9,5,5));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 10,6,5));

    final HashMap<User, Double> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 20.);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 38.46);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 55.55);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getRightClickPercentRate());
    }
  }

  @Test
  void test_getNbRapidClicks() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,1,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 6,2,4));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 7,3,3));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 8,4,2));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 9,5,1));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 10,6,0));

    final HashMap<User, Integer> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 5);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 7);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 3);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getNbRapidClicks());
    }
  }

  @Test
  void test_getRapidClickPercentRate() {
    // TODO: correct this test

    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    ArrayList<Player> players3 = new ArrayList<>();
    players1.add(new Player(new BigDecimal(1), new Game(), new User(), 10, false, 5,1,5));
    players2.add(new Player(new BigDecimal(2), new Game(), new User(), 10, true, 6,2,4));
    players2.add(new Player(new BigDecimal(3), new Game(), new User(), 10, false, 7,3,3));
    players3.add(new Player(new BigDecimal(4), new Game(), new User(), 10, true, 8,4,2));
    players3.add(new Player(new BigDecimal(5), new Game(), new User(), 10, true, 9,5,1));
    players3.add(new Player(new BigDecimal(6), new Game(), new User(), 10, true, 10,6,0));

    final HashMap<User, Double> TESTS = new HashMap<>() {{
      put(new User(new BigDecimal(1), "username1", "email1", "password1",
        new Date(), User.Gender.MALE, players1), 100.);
      put(new User(new BigDecimal(2), "username2", "email2", "password2",
        new Date(), User.Gender.FEMALE,players2), 53.84);
      put(new User(new BigDecimal(3), "username3", "email3", "password3",
        new Date(), User.Gender.OTHER,players3), 11.11);
    }};
    for (User user : TESTS.keySet()) {
      assertEquals(TESTS.get(user), user.getRapidClickPercentRate());
    }
  }

  @Test
  void testToString() {
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

  @Test
  void testGetGender() {

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
}
