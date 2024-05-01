/*
 * Game.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Représentation d'une partie de jeu
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
@Entity
@Table(name = "game")
public class Game implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @Transient
  private int currentRound = 0;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date createdAt;


  @Column(name = "difficulty")
  @Enumerated(EnumType.STRING)
  private Difficulty difficulty;

  @Column(name = "nb_rounds")
  private int nbRounds;

  @Column(name = "nb_colors")
  private int nbColors;

  @Column(name = "nb_values_per_color")
  private int nbValuesPerColor;

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Player> players;

  @Transient
  private Deck deck;

  @Column(name = "timer")
  private int timer;

  @Transient
  public static final int TIMER_MIN = 10;

  @Transient
  public static final  int TIMER_MAX = 60;

  @Transient
  public static final int NB_ROUNDS_MIN = 3;

  @Transient
  private GameState gameState = GameState.WAITING;

  /**
   * Constructeur par défaut
   */
  public Game() {
    this.createdAt = new Date();
    this.players = new ArrayList<>();
    this.timer = 0;
  }

  /**
   * Constructeur avec la difficulté
   *
   * @param difficulty la difficulté de la partie
   * @see Difficulty
   * @param timer le timer de chaque round en seconde
   * @param nbRounds le nombre de tours de la partie
   * @param nbColors le nombre de couleurs présente dans le deck
   * @param nbValuesPerColor le nombre de valeurs par couleur
   */
  public Game(Difficulty difficulty, int timer, int nbRounds, int nbColors, int nbValuesPerColor) {
    if (isValidNumberRound(nbRounds, nbColors, nbValuesPerColor)){
      throw new IllegalArgumentException("Le nombre de tours doit être compris entre 2 et " + nbColors * nbValuesPerColor);
    }
    if (timer < TIMER_MIN || timer > TIMER_MAX){
      throw new IllegalArgumentException("Le timer doit être compris entre" + TIMER_MIN + "  et " + TIMER_MAX + " secondes");
    }
    this.difficulty = difficulty;
    this.nbRounds = nbRounds;
    this.nbColors = nbColors;
    this.nbValuesPerColor = nbValuesPerColor;
    this.deck = new Deck(nbColors, nbValuesPerColor);
    this.deck.shuffle();
    this.players = new ArrayList<>();
    this.createdAt = new Date();
    this.timer = timer;
  }

  /**
   * Constructeur d'une partie de jeu
   *
   * @param id         l'identifiant de la partie
   * @param createdAt  la date de création de la partie
   * @param difficulty la difficulté de la partie
   * @param nbRounds le nombre de tours de la partie
   * @param nbColors le nombre de couleurs présente dans le deck
   * @param nbValuesPerColor le nombre de valeurs par couleur
   * @param players    les joueurs de la partie
   */
  public Game(BigDecimal id, Date createdAt, Difficulty difficulty, int timer ,int nbRounds, int nbColors, int nbValuesPerColor, ArrayList<Player> players) {
    if (isValidNumberRound(nbRounds, nbColors, nbValuesPerColor)){
      throw new IllegalArgumentException("Le nombre de tours doit être compris entre 1 et " + nbColors * nbValuesPerColor);
    }
    if (timer < TIMER_MIN || timer > TIMER_MAX){
      throw new IllegalArgumentException("Le timer doit être compris entre" + TIMER_MIN + "  et " + TIMER_MAX + " secondes");
    }
    this.id = id;
    this.createdAt = createdAt;
    this.difficulty = difficulty;
    this.nbRounds = nbRounds;
    this.nbColors = nbColors;
    this.nbValuesPerColor = nbValuesPerColor;
    this.players = players;
    this.deck = new Deck(nbColors, nbValuesPerColor);
    this.timer = timer;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt);
  }

  /**
   * @return l'identifiant de la partie
   */
  public BigDecimal getId() {
    return id;
  }

  /**
   * @return la date de création de la partie
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * @return la difficulté de la partie
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * @param difficulty la nouvelle difficulté de la partie
   * @see Difficulty
   */
  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * @return le nombre de tours de la partie
   */
  public int getNbRounds() {
    return nbRounds;
  }

  /**
   * @param nbRounds le nouveau nombre de tours de la partie
   */
  public void setNbRounds(int nbRounds) {
    if (nbRounds < NB_ROUNDS_MIN || nbRounds > nbColors * nbValuesPerColor){
      throw new IllegalArgumentException("Le nombre de tours doit être compris entre " + NB_ROUNDS_MIN + " et " + nbColors * nbValuesPerColor);
    }
    if (nbColors < Deck.NB_COLORS_MIN || nbColors > Deck.NB_COLORS_MAX) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre" + NB_ROUNDS_MIN + " et " + Deck.NB_COLORS_MAX);
    }
    if (nbValuesPerColor < Deck.NB_VALUES_PER_COLOR_MIN || nbValuesPerColor > Deck.NB_VALUES_PER_COLOR_MAX) {
      throw new IllegalArgumentException("Le nombre de valeurs par couleur doit être compris entre " + Deck.NB_VALUES_PER_COLOR_MIN + " et " + Deck.NB_VALUES_PER_COLOR_MAX);
    }
    this.nbRounds = nbRounds;
  }

  /**
   * @return le nombre de couleurs présente dans le deck
   */
  public int getNbColors() {
    return nbColors;
  }

  /**
   * @param nbColors le nouveau nombre de couleurs présente dans le deck
   */
  public void setNbColors(int nbColors) {
    if (nbColors < Deck.NB_COLORS_MIN || nbColors > Deck.NB_COLORS_MAX) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre " + Deck.NB_COLORS_MIN + " et " + Deck.NB_COLORS_MAX);
    }
    this.nbColors = nbColors;
  }

  /**
   * @return le nombre de valeurs par couleur
   */
  public int getNbValuesPerColor() {
    return nbValuesPerColor;
  }

  /**
   * @param nbValuesPerColor le nouveau nombre de valeurs par couleur
   */
  public void setNbValuesPerColor(int nbValuesPerColor) {
    if (nbValuesPerColor < Deck.NB_VALUES_PER_COLOR_MIN || nbValuesPerColor > Deck.NB_VALUES_PER_COLOR_MAX) {
      throw new IllegalArgumentException("Le nombre de valeurs par couleur doit être compris entre " + Deck.NB_VALUES_PER_COLOR_MIN + " et " + Deck.NB_VALUES_PER_COLOR_MAX);
    }
    this.nbValuesPerColor = nbValuesPerColor;
  }

  /**
   * @return les joueurs de la partie
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Modifie les joueurs de la partie
   *
   * @param players les nouveaux joueurs
   */
  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  /**
   * @return le nombre de joueurs de la partie
   */
  public int getNbPlayers() {
    return this.players.size();
  }

  /**
   * Ajoute un joueur à la partie
   *
   * @param player le joueur à ajouter
   */
  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public int getTimer() {
    return timer;
  }

  public void setTimer(int timer) {
    this.timer = timer;
  }

  public Deck getDeck() {
    if (deck == null) {
      deck = new Deck(nbColors, nbValuesPerColor);
      deck.shuffle();
    }
    return deck;
  }

  /**
   * Tri des joueurs de la partie par score puis par rapidité
   */
  public void sortPlayersByScoreAndRapidity() {
    players.sort((p1, p2) -> {
      if (p1.getScore() == p2.getScore()) {
        return p2.getRapidClickCount() - p1.getRapidClickCount();
      }
      return p2.getScore() - p1.getScore();
    });
  }

  /**
   * Récupère le gagnant de la partie
   *
   * @return le nom du gagnant
   */
  public Player getWinner(){
    this.sortPlayersByScoreAndRapidity();
    return players.get(0);
  }

  /**
   * Vérifie si le nombre de tours est valide
   *
   * @param nbRounds le nombre de tours
   * @param nbColors le nombre de couleurs
   * @param nbValuesPerColor le nombre de valeurs par couleur
   * @return true si le nombre de tours est valide, false sinon
   */
  public boolean isValidNumberRound(int nbRounds, int nbColors, int nbValuesPerColor){
    return nbRounds < NB_ROUNDS_MIN || nbRounds > nbColors * nbValuesPerColor;
  }

  @Override
  public String toString() {
    return String.format("Game{id=%s, createdAt=%s, difficulty=%s, nbRounds=%d, nbColors=%d, nbValuesPerColor=%d}",
      id != null ? id.toString() : "null",
      createdAt != null ? createdAt.toString() : "null",
      difficulty != null ? difficulty.toString() : "null",
      nbRounds != 0 ? nbRounds : 0,
      nbColors != 0 ? nbColors : 0,
      nbValuesPerColor != 0 ? nbValuesPerColor : 0
      );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Game)) return false;
    Game game = (Game) o;
    return getNbRounds() == game.getNbRounds() && getNbColors() == game.getNbColors() && getNbValuesPerColor() == game.getNbValuesPerColor() && Objects.equals(getId(), game.getId()) && Objects.equals(getCreatedAt(), game.getCreatedAt()) && getDifficulty() == game.getDifficulty() && Objects.equals(getPlayers(), game.getPlayers()) && Objects.equals(getDeck(), game.getDeck());
  }

  /**
   * Difficulté possible d'une partie
   */
  public enum Difficulty {EASY, HARD}

  public enum GameState {WAITING, STARTED, FINISHED}

  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public int getCurrentRound() {
    return currentRound;
  }

  public boolean nextRound() {
    currentRound++;

    if (currentRound >= nbRounds) gameState = GameState.FINISHED;
    return gameState != GameState.FINISHED;
  }
}

