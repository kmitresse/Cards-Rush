package project.pojo;

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
  @Column(name = "id_player")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @ManyToOne
  @JoinColumn(name = "id_game", nullable = false)
  private Game game;

  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  private User user;

  @Column(name = "score")
  private int score;

  @Column(name = "winner")
  private boolean winner;

  @Column(name = "nb_clicks")
  private int nbClicks;

  @Column(name = "nb_right_clicks")
  private int nbRightClicks;

  @Column(name = "nb_rapid_clicks")
  private int nbRapidClick;

  public Player() {

  }

  public Player(Game game, User user) {
    this.game = game;
    this.user = user;
    this.score = 0;
    this.winner = false;
    this.nbClicks = 0;
    this.nbRightClicks = 0;
    this.nbRapidClick = 0;
  }

  public Player(Game game, User user, int score, boolean winner, int nbClicks, int nbRightClicks, int nbRapidClick) {
    this.game = game;
    this.user = user;
    this.score = score;
    this.winner = winner;
    this.nbClicks = nbClicks;
    this.nbRightClicks = nbRightClicks;
    this.nbRapidClick = nbRapidClick;
  }

  @Override
  public int hashCode() {
    return Objects.hash(game, user, score, winner, nbClicks, nbRightClicks, nbRapidClick);
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

  public int getNbClicks() {
    return nbClicks;
  }

  public void setNbClicks(int nbClicks) {
    this.nbClicks = nbClicks;
  }

  public void addClick() {
    this.nbClicks++;
  }

  public int getNbRightClicks() {
    return nbRightClicks;
  }

  public void setNbRightClicks(int nbRightClicks) {
    this.nbRightClicks = nbRightClicks;
  }

  public void addRightClick() {
    this.nbRapidClick++;
  }
  public int getNbRapidClick() {
    return nbRapidClick;
  }

  public void setNbRapidClick(int nbRapidClick) {
    this.nbRapidClick = nbRapidClick;
  }

  public void addRapidClick() {
    this.nbRapidClick++;
  }

  @Override
  public String toString() {
    return "Player{" +
      "game=" + game +
      ", user=" + user +
      ", score=" + score +
      ", winner=" + winner +
      ", nbClicks=" + nbClicks +
      ", nbRightClicks=" + nbRightClicks +
      ", nbRapidClick=" + nbRapidClick +
      '}';
  }
}
