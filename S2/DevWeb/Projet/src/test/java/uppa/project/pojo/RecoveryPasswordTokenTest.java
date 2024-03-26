package uppa.project.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecoveryPasswordTokenTest {

  static RecoveryPasswordToken[] FIXTURE;
  @BeforeAll
  static void setUp() {
    FIXTURE = new RecoveryPasswordToken[] {
      new RecoveryPasswordToken(1, "token1", new User(new BigDecimal(1), "username1", "password1", "email1", new Date(1996, 1,4), User.Gender.MALE, new ArrayList<Player>()),new Date(2024,1,2)),
      new RecoveryPasswordToken(2, "token2", new User(new BigDecimal(2), "username2", "password2", "email2", new Date(1996, 2,5), User.Gender.FEMALE, new ArrayList<Player>()),new Date(2024,3,4)),
      new RecoveryPasswordToken(3, "token3", new User(new BigDecimal(3), "username3", "password3", "email3", new Date(1996, 3,6), User.Gender.OTHER, new ArrayList<Player>()), new Date(2024,5,6))
    };
   }
  @Test
  void getId() {
    assertEquals(1, FIXTURE[0].getId());
    assertEquals(2, FIXTURE[1].getId());
    assertEquals(3, FIXTURE[2].getId());
  }

  @Test
  void getToken() {
    assertEquals("token1", FIXTURE[0].getToken());
    assertEquals("token2", FIXTURE[1].getToken());
    assertEquals("token3", FIXTURE[2].getToken());
  }

  @Test
  void setToken() {
    String[] expected = new String[]{"token1", "token2", "token3"};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setToken(expected[i]);
      assertEquals(expected[i], FIXTURE[i].getToken());
    }
  }

  @Test
  void getUser() {
    User[] expected = new User[]{
      new User(new BigDecimal(1), "username1", "password1", "email1", new Date(1996, 1, 4), User.Gender.MALE, new ArrayList<Player>()),
      new User(new BigDecimal(2), "username2", "password2", "email2", new Date(1996, 2, 5), User.Gender.FEMALE, new ArrayList<Player>()),
      new User(new BigDecimal(3), "username3", "password3", "email3", new Date(1996, 3, 6), User.Gender.OTHER, new ArrayList<Player>())};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i].getId(), FIXTURE[i].getUser().getId());
      assertEquals(expected[i].getUsername(), FIXTURE[i].getUser().getUsername());
      assertEquals(expected[i].getPassword(), FIXTURE[i].getUser().getPassword());
      assertEquals(expected[i].getEmail(), FIXTURE[i].getUser().getEmail());
      assertEquals(expected[i].getBirth(), FIXTURE[i].getUser().getBirth());
      assertEquals(expected[i].getGender(), FIXTURE[i].getUser().getGender());
      assertEquals(expected[i].getPlayedGames(), FIXTURE[i].getUser().getPlayedGames());
    }
  }

  @Test
  void setUser() {
    User[] expected = new User[]{
      new User(new BigDecimal(4), "username4", "password4", "email4", new Date(1996, 4, 7), User.Gender.MALE, new ArrayList<Player>()),
      new User(new BigDecimal(5), "username5", "password5", "email5", new Date(1996, 5, 8), User.Gender.FEMALE, new ArrayList<Player>()),
      new User(new BigDecimal(6), "username6", "password6", "email6", new Date(1996, 6, 9), User.Gender.OTHER, new ArrayList<Player>())};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setUser(expected[i]);
      assertEquals(expected[i].getId(), FIXTURE[i].getUser().getId());
      assertEquals(expected[i].getUsername(), FIXTURE[i].getUser().getUsername());
      assertEquals(expected[i].getPassword(), FIXTURE[i].getUser().getPassword());
      assertEquals(expected[i].getEmail(), FIXTURE[i].getUser().getEmail());
      assertEquals(expected[i].getBirth(), FIXTURE[i].getUser().getBirth());
      assertEquals(expected[i].getGender(), FIXTURE[i].getUser().getGender());
      assertEquals(expected[i].getPlayedGames(), FIXTURE[i].getUser().getPlayedGames());
    }
  }

  @Test
  void getExpiresAt() {
    Date[] expected = new Date[]{
      new Date(2024, 1, 2),
      new Date(2024, 3, 4),
      new Date(2024, 5, 6)};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getExpiresAt());
    }
  }

  @Test
  void setExpiresAt() {
    Date[] expected = new Date[]{
      new Date(2024, 2, 3),
      new Date(2024, 4, 5),
      new Date(2024, 6, 7)};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setExpiresAt(expected[i]);
      assertEquals(expected[i], FIXTURE[i].getExpiresAt());
    }
  }

  @Test
  void to_string_return_the_rigth_format() {
    // TODO Implement this method
    fail();
  }

  @Test
  void getExpirationDate() {
    Date[] expected = new Date[]{
      new Date(2024, 1, 2),
      new Date(2024, 3, 4),
      new Date(2024, 5, 6)};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getExpirationDate());
    }
  }
}
