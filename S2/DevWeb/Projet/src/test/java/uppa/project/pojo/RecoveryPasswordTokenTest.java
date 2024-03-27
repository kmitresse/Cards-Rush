package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RecoveryPasswordTokenTest {

//  static RecoveryPasswordToken[] FIXTURE;

//  @BeforeAll
//  static void setUp() {
//    FIXTURE = new RecoveryPasswordToken[]{new RecoveryPasswordToken(1, "token1", new User(new BigDecimal(1), "username1", "password1",
//      "email1", new Date(1996, 1, 4), User.Gender.MALE, new ArrayList<Player>()), new Date(2024, 1, 2)), new RecoveryPasswordToken(2,
//      "token2", new User(new BigDecimal(2), "username2", "password2", "email2", new Date(1996, 2, 5), User.Gender.FEMALE,
//      new ArrayList<Player>()), new Date(2024, 3, 4)), new RecoveryPasswordToken(3, "token3", new User(new BigDecimal(3), "username3",
//      "password3", "email3", new Date(1996, 3, 6), User.Gender.OTHER, new ArrayList<Player>()), new Date(2024, 5, 6))};
//  }

  @Test
  void test_constructor() {
    new RecoveryPasswordToken(new BigDecimal(1), "token1", new User(), new Date(124, Calendar.FEBRUARY, 2));
    new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(), new Date(124, Calendar.APRIL, 4));
    new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date(124, Calendar.JUNE, 6));
  }

  @Test
  void test_getId() {
    HashMap<RecoveryPasswordToken, BigDecimal> TESTS = new HashMap<>() {{
      put(new RecoveryPasswordToken(new BigDecimal(1), "token1", new User(), new Date()), new BigDecimal(1));
      put(new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(), new Date()), new BigDecimal(2));
      put(new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date()), new BigDecimal(3));
    }};

    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getId());
  }

  @Test
  void test_getToken() {
    HashMap<RecoveryPasswordToken, String> TESTS = new HashMap<>() {
      {
        put(new RecoveryPasswordToken(new BigDecimal(1), "token1", new User(), new Date()), "token1");
        put(new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(), new Date()), "token2");
        put(new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date()), "token3");
      }};

    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getToken());
  }

  @Test
  void test_setToken() {
    final String EXPECTED = "new-token";
    final RecoveryPasswordToken TOKEN = new RecoveryPasswordToken(new BigDecimal(1), "token", new User(), new Date());

    TOKEN.setToken(EXPECTED);
    assertEquals(EXPECTED, TOKEN.getToken());
  }

  @Test
  void test_getUser() {
    final HashMap<RecoveryPasswordToken, User> TESTS = new HashMap<>() {{
      put(
        new RecoveryPasswordToken(
          new BigDecimal(1),
          "token1",
          new User(
            new BigDecimal(1),
            "username1",
            "password1",
            "email1",
            new Date(196, Calendar.FEBRUARY, 4),
            User.Gender.MALE,
            null
          ),
          new Date()),
        new User(
          new BigDecimal(1),
          "username1",
          "password1",
          "email1",
          new Date(196, Calendar.FEBRUARY, 4),
          User.Gender.MALE,
          null
        )
      );
      // TODO Add more tests
    }};

    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getUser());
  }

  @Test
  void set_user() {
//    User[] expected = new User[]{new User(new BigDecimal(4), "username4", "password4", "email4", new Date(1996, 4, 7), User.Gender.MALE,
//      new ArrayList<Player>()), new User(new BigDecimal(5), "username5", "password5", "email5", new Date(1996, 5, 8), User.Gender.FEMALE,
//      new ArrayList<Player>()), new User(new BigDecimal(6), "username6", "password6", "email6", new Date(1996, 6, 9), User.Gender.OTHER,
//      new ArrayList<Player>())};
//    for (int i = 0; i < FIXTURE.length; i++) {
//      FIXTURE[i].setUser(expected[i]);
//      assertEquals(expected[i].getId(), FIXTURE[i].getUser().getId());
//      assertEquals(expected[i].getUsername(), FIXTURE[i].getUser().getUsername());
//      assertEquals(expected[i].getPassword(), FIXTURE[i].getUser().getPassword());
//      assertEquals(expected[i].getEmail(), FIXTURE[i].getUser().getEmail());
//      assertEquals(expected[i].getBirth(), FIXTURE[i].getUser().getBirth());
//      assertEquals(expected[i].getGender(), FIXTURE[i].getUser().getGender());
//      assertEquals(expected[i].getPlayedGames(), FIXTURE[i].getUser().getPlayedGames());
//    }
    // TODO Implement this method
    fail();
  }

  @Test
  void get_expires_at() {
//    Date[] expected = new Date[]{new Date(2024, 1, 2), new Date(2024, 3, 4), new Date(2024, 5, 6)};
//    for (int i = 0; i < FIXTURE.length; i++) {
//      assertEquals(expected[i], FIXTURE[i].getExpiresAt());
//    }
    // TODO Implement this method
    fail();
  }

  @Test
  void set_expires_at() {
//    Date[] expected = new Date[]{new Date(2024, 2, 3), new Date(2024, 4, 5), new Date(2024, 6, 7)};
//    for (int i = 0; i < FIXTURE.length; i++) {
//      FIXTURE[i].setExpiresAt(expected[i]);
//      assertEquals(expected[i], FIXTURE[i].getExpiresAt());
//    }
    // TODO Implement this method
    fail();
  }

  @Test
  void get_expiration_date() {
//    Date[] expected = new Date[]{new Date(2024, 1, 2), new Date(2024, 3, 4), new Date(2024, 5, 6)};
//    for (int i = 0; i < FIXTURE.length; i++) {
//      assertEquals(expected[i], FIXTURE[i].getExpirationDate());
//    }
    // TODO Implement this method
    fail();
  }

  @Test
  void to_string_return_the_rigth_format() {
    // TODO Implement this method
    fail();
  }
}
