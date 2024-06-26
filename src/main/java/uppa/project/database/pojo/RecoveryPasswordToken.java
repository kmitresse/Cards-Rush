package uppa.project.database.pojo;

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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
  private BigDecimal id;

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
    this.expiresAt = new Date(new Date().getTime() + 600000);
  }

  /**
   * Constructeur depuis la base de données
   *
   * @param id
   * @param token
   * @param user
   */
  public RecoveryPasswordToken(BigDecimal id, String token, User user, Date expiresAt) {
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
  public BigDecimal getId() {
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
  public int hashCode() {
    return Objects.hash(getId(), getToken(), getUser(), getExpiresAt());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RecoveryPasswordToken)) return false;
    RecoveryPasswordToken that = (RecoveryPasswordToken) o;
    return Objects.equals(getId(), that.getId()) && Objects.equals(getToken(), that.getToken()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getExpiresAt(), that.getExpiresAt());
  }

  @Override
  public String toString() {
    return String.format("RecoveryPasswordToken{id=%s, token=%s, user=%s, expiresAt=%s}",
      id != null ? id.toString() : "null",
      token != null ? token : "null",
      user != null ? user.toString() : "null",
      expiresAt != null ? expiresAt.toString() : "null");
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
