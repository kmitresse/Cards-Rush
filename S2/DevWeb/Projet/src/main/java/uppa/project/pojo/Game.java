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
   */
  public Game(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Constructeur d'une partie de jeu
   *
   * @param id         l'identifiant de la partie
   * @param createdAt  la date de création de la partie
   * @param difficulty la difficulté de la partie
   * @param players    les joueurs de la partie
   */
  public Game(BigDecimal id, Date createdAt, Difficulty difficulty, Set<Player> players) {
    this.id = id;
    this.createdAt = createdAt;
    this.difficulty = difficulty;
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
   * Ajoute un joueur à la partie
   *
   * @param player le joueur à ajouter
   */
  public void addPlayer(Player player) {
    this.players.add(player);
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
