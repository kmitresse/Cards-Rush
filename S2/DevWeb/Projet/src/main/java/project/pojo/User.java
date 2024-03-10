package project.pojo;

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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Entity
@Table(name = "user")
public class User implements Serializable {

  public enum Sexe {FEMME, HOMME, NONBINAIRE};

  @Id
  @Column(name = "id_user")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Temporal(TemporalType.DATE)
  @Column(name = "birth_date")
  private Date bithDate;

  @Column(name = "sexe")
  @Enumerated( EnumType.STRING)
  private Sexe sexe;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Player> playedGame;
  public User() {}
  public User(String username, String password, Date birthDate, Sexe sexe) {
    this.username = username;
    String hashedPassword = hashPassword(password);
    this.password = hashedPassword;
    this.bithDate = birthDate;
    this.sexe = sexe;

  }

  public User(BigDecimal id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, bithDate, sexe);
  }
  public BigDecimal getId(){
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
    String hashedPassword = hashPassword(password);
    this.password = hashedPassword;
  }

  public Date getBithDate() {
    return bithDate;
  }

  public void setBithDate(Date bithDate) {
    this.bithDate = bithDate;
  }

  public Sexe getSexe() {
    return sexe;
  }

  public void setSexe(Sexe sexe) {
    this.sexe = sexe;
  }

  public int getAge(){
    //TODO: Implement this function
    return 1;
  }

  private String hashPassword(String password) {
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
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", age=" + getAge() +
      ", sexe=" + sexe +
      '}';
  }
}
