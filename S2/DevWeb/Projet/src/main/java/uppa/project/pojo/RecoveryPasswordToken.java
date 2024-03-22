package uppa.project.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  @Column(name = "email")
  private String email;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "expires_at")
  private Date expiresAt;

  public RecoveryPasswordToken() {
  }

  /**
   * Constructeur
   * @param token
   * @param email
   */
  public RecoveryPasswordToken(String token, String email) {
    this.token = token;
    this.email = email;
  }

  /**
   * Constructeur depuis la base de données
   * @param id
   * @param token
   * @param email
   */
  public RecoveryPasswordToken(int id, String token, String email) {
    this.id = id;
    this.token = token;
    this.email = email;
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
   * Récupère l'email associé au token
   *
   * @return l'email associé au token
   */
  public String getEmail() {
    return email;
  }

  /**
   * Définit l'email associé au token
   *
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
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
}
