package uppa.project.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * Représentation d'un token de réinitialisation de mot de passe
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
@Entity
@Table(name = "recovery_password_token")
public class RecoveryPasswordToken {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "token")
  private String token;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "expires_at")
  private Date expiresAt;

  public RecoveryPasswordToken() {
  }

  /**
   * Constructeur
   *
   * @param token
   * @param user
   */
  public RecoveryPasswordToken(String token, User user) {
    this.token = token;
    this.user = user;
  }

  /**
   * Constructeur depuis la base de données
   *
   * @param id
   * @param token
   * @param user
   */
  public RecoveryPasswordToken(int id, String token, User user, Date expiresAt) {
    this.id = id;
    this.token = token;
    this.user = user;
    this.expiresAt = expiresAt;
  }

  /**
   * Récupère l'id de l'instance
   *
   * @return l'id
   */
  public int getId() {
    return id;
  }

  /**
   * Récupère le token
   *
   * @return le token
   */
  public String getToken() {
    return token;
  }

  /**
   * Définit le token
   *
   * @param token
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Récupère l'utilisateur associé au token
   *
   * @return l'utilisateur associé au token
   */
  public User getUser() {
    return user;
  }

  /**
   * Définit l'utilisateur associé au token
   *
   * @param user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Récupère la date d'expiration du token
   *
   * @return la date d'expiration du token
   */
  public Date getExpiresAt() {
    return expiresAt;
  }

  /**
   * Définit la date d'expiration du token
   *
   * @param expiresAt
   */
  public void setExpiresAt(Date expiresAt) {
    this.expiresAt = expiresAt;
  }

  @Override
  public String toString() {
    return "RecoveryPasswordToken{" +
      "id=" + id +
      ", token='" + token + '\'' +
      ", user=" + user +
      ", expiresAt=" + expiresAt +
      '}';
  }

  /**
   * Récupère la date d'expiration du token
   *
   * @return la date d'expiration du token
   */
  public Date getExpirationDate() {
    return expiresAt;
  }
}
