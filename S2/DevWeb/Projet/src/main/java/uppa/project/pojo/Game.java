package uppa.project.pojo;

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
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigDecimal id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="created_at")
  private Date createdAt;

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Player> players;

  public Game() {
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, players);
  }
  public BigDecimal getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
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
    return String.format("Game{id=%s, createdAt=%s, players=%s}", id.toString(), createdAt, players);
  }
}
