package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecoveryPasswordTokenTest {

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
          new User(new BigDecimal(1), "username1", "email1", "password1", new Date(96, Calendar.FEBRUARY, 4), User.Gender.MALE, null),
          new Date()),
          new User(new BigDecimal(1), "username1", "email1", "password1", new Date(96, Calendar.FEBRUARY, 4), User.Gender.MALE, null)
        );

      put(
        new RecoveryPasswordToken(
          new BigDecimal(2),
          "token2",
          new User(new BigDecimal(2), "username2", "email2", "password2", new Date(101, Calendar.MAY, 12), User.Gender.OTHER, null),
          new Date()),
          new User(new BigDecimal(2), "username2", "email2", "password2", new Date(101, Calendar.MAY, 12), User.Gender.OTHER, null)
      );
      put(
        new RecoveryPasswordToken(
          new BigDecimal(1),
          "token3",
          new User(new BigDecimal(3), "username3", "email3", "password3", new Date(100, Calendar.SEPTEMBER, 12), User.Gender.MALE, null),
          new Date()),
        new User(new BigDecimal(3), "username3", "email3", "password3", new Date(100, Calendar.SEPTEMBER, 12), User.Gender.MALE, null)
        );
    }};

    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getUser());
  }

  @Test
  void set_user() {
    final HashMap<RecoveryPasswordToken, User> TESTS = new HashMap<>() {{
      put(
        new RecoveryPasswordToken(
          new BigDecimal(1),
          "token1",
          null,
          new Date()),
        new User(new BigDecimal(1), "username000", "email000", "password000", new Date(9, Calendar.FEBRUARY, 4), User.Gender.MALE, null
        )
      );
      put(
        new RecoveryPasswordToken(
          new BigDecimal(2),
          "token2",
          new User(new BigDecimal(2), "username2", "email2", "password2", new Date(101, Calendar.MAY, 12), User.Gender.OTHER, null),
          new Date()),
        new User(new BigDecimal(1), "username000", "email000", "password000", new Date(9, Calendar.FEBRUARY, 4), User.Gender.MALE, null)
      );
      put(
        new RecoveryPasswordToken(
          new BigDecimal(3),
          "token3",
          new User(new BigDecimal(3), "username3", "email3", "password3", new Date(100, Calendar.SEPTEMBER, 12), User.Gender.MALE, null),
          new Date()),
        new User(new BigDecimal(1), "username000", "email000", "password000", new Date(9, Calendar.FEBRUARY, 4), User.Gender.MALE, null)
      );
    }};

    for (RecoveryPasswordToken token : TESTS.keySet()){
      User expected = TESTS.get(token);
      token.setUser(
        new User(new BigDecimal(1), "username000", "email000", "password000", new Date(9, Calendar.FEBRUARY, 4), User.Gender.MALE, null)
      );
      assertEquals(TESTS.get(token), token.getUser());
    }

  }

  @Test
  void get_expiresAt() {
    final HashMap<RecoveryPasswordToken, Date> TESTS = new HashMap<>() {{
      put(new RecoveryPasswordToken(new BigDecimal(1), "token1", new User(), new Date(124, Calendar.FEBRUARY, 2)), new Date(124, Calendar.FEBRUARY, 2));
      put(new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(), new Date(124, Calendar.APRIL, 4)), new Date(124, Calendar.APRIL, 4));
      put(new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date(124, Calendar.JUNE, 6)), new Date(124, Calendar.JUNE, 6));
    }};
    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getExpiresAt());

  }

  @Test
  void set_expires_at() {
    final HashMap<RecoveryPasswordToken, Date> TESTS = new HashMap<>() {{
      put(new RecoveryPasswordToken(new BigDecimal(1), "token1", new User(), new Date(124, Calendar.FEBRUARY, 2)), new Date(125, Calendar.JANUARY, 2));
      put(new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(), new Date(124, Calendar.APRIL, 4)), new Date(125, Calendar.JANUARY, 2));
      put(new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date(124, Calendar.JUNE, 6)), new Date(125, Calendar.JANUARY, 2));
    }};
    for (RecoveryPasswordToken token : TESTS.keySet()) {
      Date expected = TESTS.get(token);
      token.setExpiresAt(new Date(125, Calendar.JANUARY, 2));
      assertEquals(TESTS.get(token), token.getExpiresAt());
    }
  }

  @Test
  void test_ToString() {
    final HashMap<RecoveryPasswordToken, String> TESTS = new HashMap<>() {{
      put(new RecoveryPasswordToken(new BigDecimal(1), "token1", null, new Date(124, Calendar.FEBRUARY, 2)),
        "RecoveryPasswordToken{id=1, token=token1, user=null, expiresAt=Fri Feb 02 00:00:00 CET 2024}");
      put(new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(
          new BigDecimal(1),
          "username1",
          "email1",
          "password1",
          new Date(96, Calendar.FEBRUARY, 4),
          User.Gender.MALE,
          null
        ), new Date(124, Calendar.APRIL, 4)),
        "RecoveryPasswordToken{id=2, token=token2, " +
        "user=User{id='1', username=username1, email=email1, birth='Sun Feb 04 00:00:00 CET 1996', gender='MALE'}, " +
        "expiresAt=Thu Apr 04 00:00:00 CEST 2024}");
      put(new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date(124, Calendar.JUNE, 6)),
        "RecoveryPasswordToken{id=3, token=token3, " +
          "user=User{id='null', username=null, email=null, birth='null', gender='null'}, expiresAt=Thu Jun 06 00:00:00 CEST 2024}");
    }};
    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.toString());
  }
}
