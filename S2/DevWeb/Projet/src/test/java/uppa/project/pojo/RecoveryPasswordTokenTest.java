package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecoveryPasswordTokenTest {

  RecoveryPasswordToken[] fixture;
  static User[] usersFixture;
  @BeforeAll
  static void beforeAll() {
    usersFixture = new User[]{
      new User(new BigDecimal(1), "username1", "email1", "password1", new Date(96, Calendar.FEBRUARY, 20), User.Gender.MALE, null),
      new User(new BigDecimal(2), "username2", "email2", "password2", new Date(101, Calendar.MAY, 12), User.Gender.OTHER, null),
      new User(new BigDecimal(3), "username3", "email3", "password3", new Date(100, Calendar.SEPTEMBER, 30), User.Gender.FEMALE, null)
    };
  }

  @BeforeEach
  void beforeEach() {
    fixture = new RecoveryPasswordToken[]{
      new RecoveryPasswordToken(
        new BigDecimal(1),
        "token1",
        usersFixture[0],
        new Date(124, Calendar.MARCH,28)),
      new RecoveryPasswordToken(
        new BigDecimal(2),
        "token2", usersFixture[1]
,        new Date(124, Calendar.JULY,14)),
      new RecoveryPasswordToken(
        new BigDecimal(3),
        "token3",
        usersFixture[2],
        new Date(124, Calendar.SEPTEMBER,5))
    };
  }


  @Test
  void test_constructor() {
    new RecoveryPasswordToken(new BigDecimal(1), "token1", new User(), new Date(124, Calendar.FEBRUARY, 2));
    new RecoveryPasswordToken(new BigDecimal(2), "token2", new User(), new Date(124, Calendar.APRIL, 4));
    new RecoveryPasswordToken(new BigDecimal(3), "token3", new User(), new Date(124, Calendar.JUNE, 6));
  }

  @Test
  void test_getId() {
    HashMap<RecoveryPasswordToken, BigDecimal> TESTS = new HashMap<>() {{
      put(fixture[0], new BigDecimal(1));
      put(fixture[1], new BigDecimal(2));
      put(fixture[2], new BigDecimal(3));
    }};

    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getId());
  }

  @Test
  void test_getToken() {
    HashMap<RecoveryPasswordToken, String> TESTS = new HashMap<>() {
      {
        put(fixture[0], "token1");
        put(fixture[1], "token2");
        put(fixture[2], "token3");
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
    final HashMap<RecoveryPasswordToken, User> TESTS = new HashMap<>() {
      {
        put(fixture[0], usersFixture[0]);
        put(fixture[1], usersFixture[1]);
        put(fixture[2], usersFixture[2]);
      }};
    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getUser());
  }

  @Test
  void test_setUser() {
  RecoveryPasswordToken token = fixture[0];
  assertEquals(usersFixture[0], token.getUser());

  User newUser = usersFixture[1];
  token.setUser(newUser);
  assertEquals(newUser, token.getUser());
  }

  @Test
  void test_getExpiresAt() {
    final HashMap<RecoveryPasswordToken, Date> TESTS = new HashMap<>() {{
      put(fixture[0], new Date(124, Calendar.MARCH, 28));
      put(fixture[1], new Date(124, Calendar.JULY, 14));
      put(fixture[2], new Date(124, Calendar.SEPTEMBER, 5));
    }};
    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.getExpiresAt());

  }

  @Test
  void test_setExpiresAt() {
    RecoveryPasswordToken token = fixture[0];
    assertEquals(new Date(124, Calendar.MARCH, 28), token.getExpiresAt());
    Date newDate = new Date(124, Calendar.FEBRUARY, 3);
    token.setExpiresAt(newDate);
    assertEquals(newDate, token.getExpiresAt());
  }

  @Test
  void test_ToString() {
    final HashMap<RecoveryPasswordToken, String> TESTS = new HashMap<>() {{
      put(fixture[0],
        "RecoveryPasswordToken{id=1, token=token1, " +
        "user=User{id='1', username=username1, email=email1, birth='Tue Feb 20 00:00:00 CET 1996', gender='MALE'}, " +
        "expiresAt=Thu Mar 28 00:00:00 CET 2024}");
      put(fixture[1],
        "RecoveryPasswordToken{id=2, token=token2, " +
        "user=User{id='2', username=username2, email=email2, birth='Sat May 12 00:00:00 CEST 2001', gender='OTHER'}, " +
        "expiresAt=Sun Jul 14 00:00:00 CEST 2024}");
      put(fixture[2],
        "RecoveryPasswordToken{id=3, token=token3, " +
        "user=User{id='3', username=username3, email=email3, birth='Sat Sep 30 00:00:00 CEST 2000', gender='FEMALE'}, " +
        "expiresAt=Thu Sep 05 00:00:00 CEST 2024}");
    }};
    for (RecoveryPasswordToken token : TESTS.keySet())
      assertEquals(TESTS.get(token), token.toString());
  }
}
