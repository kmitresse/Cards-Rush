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
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
  private ArrayList<Player> players;

  @Transient
  private Deck deck;
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
    if (nbRounds < 1 || nbRounds > nbColors * nbValuesPerColor){
      throw new IllegalArgumentException("Le nombre de tours doit être supérieur ou égal à 1");
    }
    if (nbColors < 1 || nbColors > Card.Color.values().length) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre 1 et " + Card.Color.values().length);
    }
    if (nbValuesPerColor < 1 || nbValuesPerColor > Card.Value.values().length) {
      throw new IllegalArgumentException("Le nombre de valeurs par couleur doit être compris entre 1 et " + Card.Value.values().length);
    }
    this.nbRounds = nbRounds;
    this.nbColors = nbColors;
    this.nbValuesPerColor = nbValuesPerColor;
    this.deck = new Deck(nbColors, nbValuesPerColor);
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
  public Game(BigDecimal id, Date createdAt, Difficulty difficulty, int nbRounds, int nbColors, int nbValuesPerColor, ArrayList<Player> players) {
    this.id = id;
    this.createdAt = createdAt;
    this.difficulty = difficulty;
    if (nbRounds < 1 || nbRounds > nbColors * nbValuesPerColor){
      throw new IllegalArgumentException("Le nombre de tours doit être compris entre 1 et " + nbColors * nbValuesPerColor);
    }
    if (nbColors < 1 || nbColors > Card.Color.values().length) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre 1 et " + Card.Color.values().length);
    }
    if (nbValuesPerColor < 1 || nbValuesPerColor > Card.Value.values().length) {
      throw new IllegalArgumentException("Le nombre de valeurs par couleur doit être compris entre 1 et " + Card.Value.values().length);
    }
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
    if (nbRounds < 1 || nbRounds > nbColors * nbValuesPerColor){
      throw new IllegalArgumentException("Le nombre de tours doit être compris entre 1 et " + nbColors * nbValuesPerColor);
    }
    if (nbColors < 1 || nbColors > Card.Color.values().length) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre 1 et " + Card.Color.values().length);
    }
    if (nbValuesPerColor < 1 || nbValuesPerColor > Card.Value.values().length) {
      throw new IllegalArgumentException("Le nombre de valeurs par couleur doit être compris entre 1 et " + Card.Value.values().length);
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
    if (nbColors < 1 || nbColors > Card.Color.values().length) {
      throw new IllegalArgumentException("Le nombre de couleurs doit être compris entre 1 et " + Card.Color.values().length);
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
    if (nbValuesPerColor < 1 || nbValuesPerColor > Card.Value.values().length) {
      throw new IllegalArgumentException("Le nombre de valeurs par couleur doit être compris entre 1 et " + Card.Value.values().length);
    }
    this.nbValuesPerColor = nbValuesPerColor;
  }

  /**
   * @return les joueurs de la partie
   */
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * Modifie les joueurs de la partie
   *
   * @param players les nouveaux joueurs
   */
  public void setPlayers(ArrayList<Player> players) {
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

  public ArrayList<Card> getDeck() {
    return deck.getCards();
  }

  /**
   * Tri des joueurs de la partie par score
   */
  public void sortPlayersByScore() {
    for (int i = 0; i < players.size(); i++) {
      for (int j = i + 1; j < players.size(); j++) {
        if (players.get(i).getScore() < players.get(j).getScore()) {
          Player temp = players.get(i);
          players.set(i, players.get(j));
          players.set(j, temp);
        }
      }
    }
  }

  @Override
  public String toString() {
    return String.format("Game{id=%s, createdAt=%s}", id.toString(), createdAt.toString());
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
}

