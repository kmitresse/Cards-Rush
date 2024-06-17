/*
 * Player.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.websocket.Session;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import uppa.project.json.websocket.ClickChoice;

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

  @Transient
  private int tmpRightClickCount;

  @Transient
  private int partialRightClickCount;

  @Transient
  private int tmpPartialRightClickCount;

  @Column(name = "rapid_click_count")
  private int rapidClickCount;

  @Transient
  private Deck deck;

  @Transient
  private Session session = null;

  @Transient
  private ClickChoice currentClick = null;


  /**
   * Constructeur par défaut
   */
  public Player() {
  }

  public Player(Game game, User user, Session session) {
    this.game = game;
    this.user = user;
    this.session = session;
    this.score = 0;
    this.winner = false;
    this.clickCount = 0;
    this.rightClickCount = 0;
    this.rapidClickCount = 0;
    this.deck = new Deck(game.getNbColors(), game.getNbValuesPerColor());
    this.deck.shuffle();
    this.partialRightClickCount = 0;
    this.tmpRightClickCount = 0;
    this.tmpPartialRightClickCount = 0;
  }

  /**
   * Constructeur d'un joueur
   *
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
    this.deck = new Deck(game.getNbColors(), game.getNbValuesPerColor());
    this.deck.shuffle();
    this.partialRightClickCount = 0;
    this.tmpRightClickCount = 0;
    this.tmpPartialRightClickCount = 0;
  }

  /**
   * Constructeur d'un joueur
   *
   * @param game            la partie de jeu
   * @param user            l'utilisateur
   * @param score           le score
   * @param winner          si le joueur est gagnant
   * @param clickCount      le nombre de clics
   * @param rightClickCount le nombre de clics corrects
   * @param rapidClickCount le nombre de clics rapides
   */
  public Player(BigDecimal id, Game game, User user, int score, boolean winner, int clickCount, int rightClickCount, int rapidClickCount) {
    this.id = id;
    this.game = game;
    this.user = user;
    this.score = score;
    this.winner = winner;
    this.clickCount = clickCount;
    this.rightClickCount = rightClickCount;
    this.rapidClickCount = rapidClickCount;
    this.partialRightClickCount = 0;
    this.tmpRightClickCount = 0;
    this.tmpPartialRightClickCount = 0;

    this.deck = new Deck(game.getNbColors(), game.getNbValuesPerColor());
    this.deck.shuffle();
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
  public void addToScore(int points) {
    this.score += points;
  }

  /**
   * @return le score maximum possible
   */
  public int getScoreMax(){
    return game.getNbRounds() * 3; // 2 points par bonne réponse + 1 point pour le clic rapide
  }
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
   * @return le nombre de clics corrects du joueur
   */
  public int getTmpRightClickCount() {
    return tmpRightClickCount;
  }

  /**
   * Incrémente de 1 le nombre de clics corrects
   */
  public void incrementTmpRightClickCount() {
    tmpRightClickCount++;
  }



  /**
   * @return le pourcentage de clics corrects du joueur sur la partie courante
   */
  public double getRatioRightClick() {
    if (clickCount == 0 || rightClickCount == 0) return 0;
    return (double) Math.abs(rightClickCount * 10000 / game.getNbRounds()) / 100;
  }

  /**
   * @return le nombre de clics rapides
   */
  public int getRapidClickCount() {
    return rapidClickCount;
  }

  /**
   * @return le nombre de clics partiels corrects
   */
  public int getPartialRightClickCount() {
    return partialRightClickCount;
  }

  /**
   * Incrémente de 1 le nombre de clics partielement corrects
   */
  public void incrementPartialRightClickCount() {
    partialRightClickCount++;
  }

  /**
   * @return le nombre de clics corrects du joueur
   */
  public int getTmpPartialRightClickCount() {
    return tmpPartialRightClickCount;
  }

  /**
   * Incrémente de 1 le nombre de clics corrects
   */
  public void incrementTmpPartialRightClickCount() {
    tmpPartialRightClickCount++;
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

  /**
   * @return le pourcentage de clics rapides du joueur sur la partie courante
   */
  public double getRatioRapidClick() {
    if (clickCount == 0 || rapidClickCount == 0) return 0;
    return (double) Math.abs(rapidClickCount * 10000 / game.getNbRounds()) / 100;
  }

  public Deck getDeck() {
    return deck;
  }

  @Override
  public String toString() {
    return String.format("Player{id=%s, game=%s, user=%s, score=%d, winner=%b, clickCount=%d, rightClickCount=%d, rapidClickCount=%d}",
      id != null ? id.toString() : "null", game != null ? game.toString() : "null", user != null ? user.toString() : "null", score,
      winner, clickCount, rightClickCount, rapidClickCount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;
    Player player = (Player) o;
    return getScore() == player.getScore() && isWinner() == player.isWinner() && getClickCount() == player.getClickCount() && getRightClickCount() == player.getRightClickCount() && getRapidClickCount() == player.getRapidClickCount() && Objects.equals(id, player.id) && Objects.equals(getGame(), player.getGame()) && Objects.equals(getUser(), player.getUser());
  }

  /**
   * @return l'identifiant
   */
  public BigDecimal getId() {
    return id;
  }

  public Session getSession() {
    return session;
  }

  public ClickChoice getCurrentClick() {
    return currentClick;
  }

  public void setCurrentClick(ClickChoice currentClick) {
    this.currentClick = currentClick;
  }


  public void setPartialRightClickCount(int tmpPartialRightClickCount) {
    this.partialRightClickCount = tmpPartialRightClickCount;
  }
}