/*
 * Player.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

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

/**
 * Représentation d'un joueur
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
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

  /**
   * Constructeur par défaut
   */
  public Player() {}

  /**
   * Constructeur d'un joueur
   * @param game la partie de jeu
   * @param user l'utilisateur
   */
  public Player(Game game, User user) {
    this.game = game;
    this.user = user;
    this.score = 0;
    this.winner = false;
    this.clickCount = 0;
    this.rightClickCount = 0;
    this.rapidClickCount = 0;
  }

  /**
   * Constructeur d'un joueur
   * @param game la partie de jeu
   * @param user l'utilisateur
   * @param score le score
   * @param winner si le joueur est gagnant
   * @param clickCount le nombre de clics
   * @param rightClickCount le nombre de clics corrects
   * @param rapidClickCount le nombre de clics rapides
   */
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

  /**
   * @return La partie de jeu du joueur
   */
  public Game getGame() {
    return game;
  }

  /**
   * Modifie la partie de jeu du joueur
   *
   * @param game le nouveau jeu
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * @return l'utilisateur
   */
  public User getUser() {
    return user;
  }

  /**
   * Modifie l'utilisateur
   *
   * @param user le nouvel utilisateur
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return l'identifiant
   */
  public int getScore() {
    return score;
  }

  /**
   * Modifie le score
   *
   * @param score le nouveau score
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Ajoute des points au score
   *
   * @param points les points à ajouter
   */
  public void updateScore(int points) { this.score += points; }

  /**
   * @return si le joueur est gagnant
   */
  public boolean isWinner() {
    return winner;
  }

  /**
   * Déclare le joueur comme gagnant
   */
  public void setWinner() {
    this.winner = true;
  }

  /**
   * @return le nombre de clics
   */
  public int getClickCount() {
    return clickCount;
  }

  /**
   * Modifie le nombre de clics
   *
   * @param clickCount le nouveau nombre de clics
   */
  public void setClickCount(int clickCount) {
    this.clickCount = clickCount;
  }

  /**
   * Incrémente de 1 le nombre de clics
   */
  public void incrementClickCount() {
    clickCount++;
  }

  /**
   * @return le nombre de clics corrects
   */
  public int getRightClickCount() {
    return rightClickCount;
  }

  /**
   * Modifie le nombre de clics corrects
   *
   * @param rightClickCount le nouveau nombre de clics corrects
   */
  public void setRightClickCount(int rightClickCount) {
    this.rightClickCount = rightClickCount;
  }

  /**
   * Incrémente de 1 le nombre de clics corrects
   */
  public void incrementRightClickCount() {
    rightClickCount++;
  }

  /**
   * @return le nombre de clics rapides
   */
  public int getRapidClickCount() {
    return rapidClickCount;
  }

  /**
   * Modifie le nombre de clics rapides
   *
   * @param rapidClickCount le nouveau nombre de clics rapides
   */
  public void setRapidClickCount(int rapidClickCount) {
    this.rapidClickCount = rapidClickCount;
  }

  /**
   * Incrémente de 1 le nombre de clics rapides
   */
  public void incrementRapidClickCount() {
    rapidClickCount++;
  }

  @Override
  public String toString() {
    return String.format("Player{id=%s, game=%s, user=%s, score=%d, winner=%b, clickCount=%d, rightClickCount=%d, rapidClickCount=%d}", id.toString(), game.toString(), user.toString(), score, winner, clickCount, rightClickCount, rapidClickCount);
  }
}
