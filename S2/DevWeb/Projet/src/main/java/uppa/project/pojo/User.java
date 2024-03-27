/*
 * User.java, 20/03/2024
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Représentation d'un utilisateur
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Temporal(TemporalType.DATE)
  @Column(name = "birth")
  private Date birth;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private ArrayList<Player> playedGames;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private ArrayList<RecoveryPasswordToken> recoveryPasswordTokens;

  /**
   * Constructeur par défaut
   */
  public User() {
  }

  /**
   * Constructeur d'un utilisateur
   *
   * @param username le pseudonyme
   * @param email    l'adresse email
   * @param password le mot de passe
   * @param birth    la date de naissance
   * @param gender   le genre
   */
  public User(String username, String email, String password, Date birth, Gender gender) {
    this.username = username;
    this.email = email;
    this.password = hashPassword(password);
    this.birth = birth;
    this.gender = gender;
  }

  /**
   * Constructeur d'un utilisateur
   *
   * @param id       l'identifiant
   * @param username le pseudonyme
   * @param email    l'adresse email
   * @param password le mot de passe
   * @param birth    la date de naissance
   * @param gender   le genre
   */
  public User(BigDecimal id, String username, String email, String password, Date birth, Gender gender, ArrayList<Player> playedGames) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.birth = birth;
    this.gender = gender;
    this.playedGames = playedGames;
  }

  /**
   * Hash le mot de passe en SHA-256
   *
   * @param password le mot de passe à hasher
   * @return le mot de passe hashé
   */
  private static String hashPassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");

      byte[] encodedhash = digest.digest(password.getBytes());

      StringBuilder hexString = new StringBuilder();
      for (byte b : encodedhash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) hexString.append('0');
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password, birth, gender);
  }

  /**
   * @return l'identifiant de l'utilisateur
   */
  public BigDecimal getId() {
    return id;
  }

  /**
   * @return le pseudonyme de l'utilisateur
   */
  public String getUsername() {
    return username;
  }

  /**
   * Modifie le pseudonyme de l'utilisateur
   *
   * @param username le nouveau pseudonyme
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return l'adresse email de l'utilisateur
   */
  public String getEmail() {
    return email;
  }

  /**
   * Modifie l'adresse email de l'utilisateur
   *
   * @param email la nouvelle adresse email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return le mot de passe hashé de l'utilisateur
   */
  public String getPassword() {
    return password;
  }

  /**
   * Modifie le mot de passe de l'utilisateur
   *
   * @param password le nouveau mot de passe
   */
  public void setPassword(String password) {
    this.password = hashPassword(password);
  }

  /**
   * @return la date de naissance de l'utilisateur
   */
  public Date getBirth() {
    return birth;
  }

  /**
   * Modifie la date de naissance de l'utilisateur
   *
   * @param birth la nouvelle date de naissance
   */
  public void setBirth(Date birth) {
    this.birth = birth;
  }

  /**
   * @return le genre de l'utilisateur
   */
  public Gender getGender() {
    return gender;
  }

  /**
   * Modifie le genre de l'utilisateur
   */
  public void setGender(Gender gender) {
    this.gender = gender;
  }

  /**
   * Calcule l'âge de l'utilisateur
   *
   * @return l'âge de l'utilisateur en années
   */
  public int getAge() {
    Date currentDate = new Date();
    long diff = currentDate.getTime() - birth.getTime();
    long diffDays = diff / (24 * 60 * 60 * 1000);
    return (int) (diffDays / 365);
  }

  /**
   * Prédicat qui vérifie si le mot de passe fourni est correct
   *
   * @param password le mot de passe à vérifier
   * @return true si le prédicat est vérifié, false sinon
   */
  public boolean verifyPassword(String password) {
    String hashedPassword = hashPassword(password);
    return hashedPassword != null && hashedPassword.equals(this.password);
  }

  /**
   * Récupère la liste des parties jouées par l'utilisateur
   *
   * @return  la liste des parties jouées
   */
  public ArrayList<Player> getPlayedGames() {
    return playedGames;
  }

  /**
   * Récupère le nombre de parties jouées
   *
   * @return le nombre de parties jouées
   */
  public int getNbPlayedGame() {
    return playedGames.size();
  }

  /**
   * Récupère le nombre de parties gagnées
   *
   * @return le nombre de parties gagnées
   */
  public int getNbWin(){
    int nbWin = 0;
    for (Player p : playedGames) {
      if (p.isWinner()) nbWin++;
    }
    return nbWin;
  }

  /**
   * Récupère le pourcentage de victoire
   *
   * @return le pourcentage de victoire
   */
  public double getWinRate(){
    return (double) getNbWin() * 100 / getNbPlayedGame();
  }

  /**
   * Récupère le nombre total de clics toute partie confondue
   *
   * @return le nombre total de clics
   */
  public int getNbClicks(){
    int nbClicks = 0;
    for (Player p : playedGames) {
      nbClicks += p.getClickCount();
    }
    return nbClicks;
  }

  /**
   * Récupère le nombre total de clics réussi toute partie confondue
   *
   * @return le nombre total de clics réussi
   */
  public int getNbRightClicks(){
    int nbRightClicks = 0;
    for (Player p : playedGames) {
      nbRightClicks += p.getRightClickCount();
    }
    return nbRightClicks;
  }

  /**
   * Récupère le pourcentage de clics réussi
   *
   * @return le pourcentage de clics réussi
   */
  public double getRightClickPercentRate(){
    return (double) getNbRightClicks() * 100 / getNbClicks();
  }

  /**
   * Récupère le nombre total de clics les plus rapides  toute partie confondue
   *
   * @return le nombre total de clics les plus rapides
   */
  public int getNbRapidClicks(){
    int nbRapidClicks = 0;
    for (Player p : playedGames) {
      nbRapidClicks += p.getRapidClickCount();
    }
    return nbRapidClicks;
  }

  /**
   * Récupère le pourcentage de clics les plus rapides
   *
   * @return le pourcentage de clics les plus rapides
   */
  public double getRapidClickPercentRate(){
    return (double) getNbRapidClicks() * 100 / getNbClicks();
  }

  @Override
  public String toString() {
    return String.format("User{username='%s', birth=%s, gender=%s}", username, birth.toString(), gender.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return
      Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getBirth(), user.getBirth()) && getGender() == user.getGender();
  }

  /**
   * Enumération des genres possibles
   */
  public enum Gender {MALE, FEMALE, OTHER}

  public static Gender getGender(String value) throws IllegalArgumentException{
    if (value.equals("MALE")) return Gender.MALE;
    if (value.equals("FEMALE")) return Gender.FEMALE;
    if (value.equals("OTHER")) return Gender.OTHER;
    throw new IllegalArgumentException("Le genre selectionné n'existe pas");
  }
}
