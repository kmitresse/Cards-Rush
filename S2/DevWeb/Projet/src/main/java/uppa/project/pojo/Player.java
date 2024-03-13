package uppa.project.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "player")
public class Player implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @ManyToOne
  @JoinColumn(name = "game_id", nullable = false)
  private Game game;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "score")
  private int score;

  @Column(name = "winner")
  private boolean winner;

  @Column(name = "click_count")
  private int clickCount;

  @Column(name = "right_click_count")
  private int rightClickCount;

  @Column(name = "rapid_click_count")
  private int rapidClickCount;

  public Player() {

  }

  public Player(Game game, User user) {
    this.game = game;
    this.user = user;
    this.score = 0;
    this.winner = false;
    this.clickCount = 0;
    this.rightClickCount = 0;
    this.rapidClickCount = 0;
  }

  public Player(Game game, User user, int score, boolean winner, int clickCount, int rightClickCount, int rapidClickCount) {
    this.game = game;
    this.user = user;
    this.score = score;
    this.winner = winner;
    this.clickCount = clickCount;
    this.rightClickCount = rightClickCount;
    this.rapidClickCount = rapidClickCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(game, user, score, winner, clickCount, rightClickCount, rapidClickCount);
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void updateScore(int points) { this.score += points; }

  public boolean isWinner() {
    return winner;
  }

  public void setWinner() {
    this.winner = true;
  }

  public int getClickCount() {
    return clickCount;
  }

  public void setClickCount(int clickCount) {
    this.clickCount = clickCount;
  }

  public void incrementClickCount() {
    clickCount++;
  }

  public int getRightClickCount() {
    return rightClickCount;
  }

  public void setRightClickCount(int rightClickCount) {
    this.rightClickCount = rightClickCount;
  }

  public void incrementRightClickCount() {
    rightClickCount++;
  }
  public int getRapidClickCount() {
    return rapidClickCount;
  }

  public void setRapidClickCount(int rapidClickCount) {
    this.rapidClickCount = rapidClickCount;
  }

  public void incrementRapidClickCount() {
    rapidClickCount++;
  }

  @Override
  public String toString() {
    return String.format("Player{id=%s, game=%s, user=%s, score=%d, winner=%b, clickCount=%d, rightClickCount=%d, rapidClickCount=%d}", id.toString(), game.toString(), user.toString(), score, winner, clickCount, rightClickCount, rapidClickCount);
  }
}
