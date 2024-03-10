package project.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game implements Serializable {

  @Id
  @Column(name = "id_game")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @Temporal(TemporalType.DATE)
  @Column(name="date")
  private Date date;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="starting_time")
  private Timestamp startTime;

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Player> players;

  public Game() {

  }

  public Game(Date date, Timestamp startTime) {
    this.date = date;
    this.startTime = startTime;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date);
  }
  public BigDecimal getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Set<Player> getPlayers() {
    return players;
  }

  public void setPlayers(Set<Player> players) {
    this.players = players;
  }

  public void addPlayers(Player player) {
    this.players.add(player);
  }



  @Override
  public String toString() {
    return "Game{" +
      "id=" + id +
      ", date=" + date +
      ", players=" + players +
      '}';
  }
}
