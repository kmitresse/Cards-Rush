/*
 * Game.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.pojo;

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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
  private Set<Player> players;

  /**
   * Constructeur par défaut
   */
  public Game() {
  }

  /**
   * Constructeur avec la difficulté
   *
   * @param difficulty la difficulté de la partie
   * @see Difficulty
   * @param nbRounds le nombre de tours de la partie
   * @param nbColors le nombre de couleurs présente dans le deck
   * @param nbValuesPerColor le nombre de valeurs par couleur
   */
  public Game(Difficulty difficulty, int nbRounds, int nbColors, int nbValuesPerColor) {
    this.difficulty = difficulty;
    this.nbRounds = nbRounds;
    this.nbColors = nbColors;
    this.nbValuesPerColor = nbValuesPerColor;
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
  public Game(BigDecimal id, Date createdAt, Difficulty difficulty, int nbRounds, int nbColors, int nbValuesPerColor, Set<Player> players) {
    this.id = id;
    this.createdAt = createdAt;
    this.difficulty = difficulty;
    this.nbRounds = nbRounds;
    this.nbColors = nbColors;
    this.nbValuesPerColor = nbValuesPerColor;
    this.players = players;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, players);
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
    this.nbValuesPerColor = nbValuesPerColor;
  }

  /**
   * @return les joueurs de la partie
   */
  public Set<Player> getPlayers() {
    return players;
  }

  /**
   * Modifie les joueurs de la partie
   *
   * @param players les nouveaux joueurs
   */
  public void setPlayers(Set<Player> players) {
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

  /**
   * Tri des joueurs de la partie par score
   */
  public void sortPlayersByScore() {
    this.players.stream().sorted((p1, p2) -> p1.getScore() - p2.getScore());
  }

  @Override
  public String toString() {
    return String.format("Game{id=%s, createdAt=%s, players=%s}", id.toString(), createdAt, players);
  }

  /**
   * Difficulté possible d'une partie
   */
  public enum Difficulty {EASY, HARD}
}
