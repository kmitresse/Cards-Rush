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
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Temporal(TemporalType.DATE)
  @Column(name = "birth")
  private Date birth;
  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Player> playedGame;
  public User() {
  }

  public User(String username, String password, Date birth, Gender gender) {
    this.username = username;
    this.password = hashPassword(password);
    this.birth = birth;
    this.gender = gender;
  }

  public User(BigDecimal id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, birth, gender);
  }

  public BigDecimal getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = hashPassword(password);
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public int getAge() {
    Date currentDate = new Date();
    long diff = currentDate.getTime() - birth.getTime();
    long diffDays = diff / (24 * 60 * 60 * 1000);
    return (int) (diffDays / 365);
  }

  public static String hashPassword(String password) {
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

  public boolean verifyPassword(String password) {
    String hashedPassword = hashPassword(password);
    return hashedPassword != null && hashedPassword.equals(this.password);
  }

  @Override
  public String toString() {
    return String.format("User{id=%s, username='%s', birth=%s, gender=%s}", id.toString(), username, birth.toString(), gender.toString());
  }

  public enum Gender {MALE, FEMALE, OTHER}
}
