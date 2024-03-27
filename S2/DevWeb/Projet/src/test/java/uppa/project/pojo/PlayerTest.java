package uppa.project.pojo;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  static Player[] FIXTURE;

  @BeforeAll
  static void setUp() {
    FIXTURE = new Player[] {
      new Player(new BigDecimal(0), new Game(), new User(), 15, false, 20, 15, 6 ),
      new Player(new BigDecimal(0), new Game(), new User(), 13, false, 15, 13, 8),
      new Player(new BigDecimal(0), new Game(), new User(), 8, false, 10, 8, 2)
    };
  }

  @Test
  void test_hash_code() {
    // TODO Implement this method
    fail();
  }

  @Test
  void get_game() {
    // TODO Implement this method
    fail();
  }

  @Test
  void set_game() {
    // TODO Implement this method
    fail();
  }

  @Test
  void get_user() {
    // TODO Implement this method
    fail();
  }

  @Test
  void set_user() {
    // TODO Implement this method
    fail();
  }

  @Test
  void get_score() {
    int[] expected = new int[]{15, 13, 8};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getScore());
    }
  }

  @Test
  void set_score() {
    int[] expected = new int[]{20, 9, 1};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setScore(expected[i]);
      assertEquals(expected[i], FIXTURE[i].getScore());
    }
  }

  @Test
  void update_score() {
    int[] expected1 = new int[]{16, 14, 9};
    int[] expected2 = new int[]{11, 9, 4};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].updateScore(1);
      assertEquals(expected1[i], FIXTURE[i].getScore());
      FIXTURE[i].updateScore(-5);
      assertEquals(expected2[i], FIXTURE[i].getScore());
    }
  }

  @Test
  void is_winner() {
    boolean[] expected = new boolean[]{false, false, false};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].isWinner());
    }
  }

  @Test
  void set_winner() {
    boolean[] expected = new boolean[]{true, true, true};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setWinner();
      assertEquals(expected[i], FIXTURE[i].isWinner());
    }
  }

  @Test
  void get_click_count() {
    int[] expected = new int[]{20, 15, 10};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getClickCount());
    }
  }

  @Test
  void set_click_count() {
    int[] expected = new int[]{25, 20, 15};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setClickCount(expected[i]);
      assertEquals(expected[i], FIXTURE[i].getClickCount());
    }
  }

  @Test
  void increment_click_count() {
    int[] expected = new int[]{21, 16, 11};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].incrementClickCount();
      assertEquals(expected[i], FIXTURE[i].getClickCount());
    }
  }

  @Test
  void get_right_click_count() {
    int[] expected = new int[]{15, 13, 8};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getRightClickCount());
    }
  }

  @Test
  void set_right_click_count() {
    int[] expected = new int[]{20, 15, 10};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setRightClickCount(expected[i]);
      assertEquals(expected[i], FIXTURE[i].getRightClickCount());
    }
  }

  @Test
  void increment_right_click_count() {
    int[] expected = new int[]{16, 14, 9};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].incrementRightClickCount();
      assertEquals(expected[i], FIXTURE[i].getRightClickCount());
    }
  }

  @Test
  void get_ratio_right_click() {
    double[] expected = new double[]{0.75*100, 0.8666666666666667*100, 0.8*100};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getRatioRightClick());
    }
  }

  @Test
  void get_rapid_click_count() {
    int[] expected = new int[]{6, 8, 2};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getRapidClickCount());
    }
  }

  @Test
  void set_rapid_click_count() {
    int[] expected = new int[]{10, 15, 20};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].setRapidClickCount(expected[i]);
      assertEquals(expected[i], FIXTURE[i].getRapidClickCount());
    }
  }

  @Test
  void increment_rapid_click_count() {
    int[] expected = new int[]{7, 9, 3};
    for (int i = 0; i < FIXTURE.length; i++) {
      FIXTURE[i].incrementRapidClickCount();
      assertEquals(expected[i], FIXTURE[i].getRapidClickCount());
    }
  }

  @Test
  void get_ratio_rapid_click() {
    double[] expected = new double[]{0.3*100, 0.5333333333333333*100, 0.2*100};
    for (int i = 0; i < FIXTURE.length; i++) {
      assertEquals(expected[i], FIXTURE[i].getRatioRapidClick());
    }
  }

  @Test
  void get_deck() {
    // TODO Implement this method
    fail();
  }

  @Test
  void test_to_string() {
    // TODO Implement this method
    fail();
  }
}
