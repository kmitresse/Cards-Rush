package uppa.project.dao.jpa;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.pojo.Game;
import uppa.project.pojo.Player;
import uppa.project.pojo.RecoveryPasswordToken;
import uppa.project.pojo.User;
import uppa.project.provider.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

class DAO_JPA_PlayerTest {

  static EntityManager entityManager;
  static DAO<Player> dao;

  static Player[] fixture;

  @BeforeAll
  static void setUp() throws DAOException {
    EntityManagerProvider.setPersitenceUnitName("test");
    entityManager = EntityManagerProvider.getInstance();
    dao = new Game_JPA_DAO_Factory().getDAOPlayer();

    assertEquals(dao.getClass(), DAO_JPA_Player.class);
  }

  @BeforeEach
  void clean() {
    fixture = new Player[] {
      new Player(
        new BigDecimal(1),
        new Game(new BigDecimal(1), new Date(100, 10, 1), Game.Difficulty.EASY, 10, 4, 2, 4, new ArrayList<>()),
        new User("user1", "email1", "password1", new Date(100, Calendar.JANUARY, 12), User.Gender.OTHER),
        2, false, 3, 1, 2
      ),
      new Player(
        new BigDecimal(2),
        new Game(new BigDecimal(1), new Date(100, 10, 1), Game.Difficulty.EASY, 10, 4, 2, 4, new ArrayList<>()),
        new User("user2", "email2", "password2", new Date(100, Calendar.MARCH, 15), User.Gender.MALE)
        , 3, true, 4, 2, 3
      ),
    };
    entityManager.getTransaction().begin();
  }

  @AfterEach
  void rollback() {
    entityManager.getTransaction().rollback();
  }

  @Test
  void findById() throws DAOException {
    // Create a new player
    dao.create(fixture[0]);
    Player player = dao.findAll()[0];

    // Find the player by its id
    Player foundPlayer = dao.findById(player.getId().intValue());
    assertEquals(player, foundPlayer);
  }

  @Test
  void findByField() throws DAOException {
    // Create players
    for (Player player : fixture) {
      dao.create(player);
    }

    // Find the players by their click count
    Player[] foundPlayers = dao.findByField("clickCount", 3);
    assertEquals(1, foundPlayers.length);
    assertEquals(3, foundPlayers[0].getClickCount());
  }

  @Test
  void findAll() throws DAOException {
    // Create players
    for (Player player : fixture) {
      dao.create(player);
    }

    // Find all the players
    Player[] foundPlayers = dao.findAll();
    assertEquals(fixture.length, foundPlayers.length);
  }

  @Test
  void create() throws DAOException {
    // Create players
    dao.create(fixture[0]);


    // Find all the players
    Player[] foundPlayers = dao.findAll();
    assertEquals(1, foundPlayers.length);

    // Check if the players are the same

    assertEquals(fixture[0].getClickCount(), foundPlayers[0].getClickCount());
    assertEquals(fixture[0].getScore(), foundPlayers[0].getScore());
    assertEquals(fixture[0].isWinner(), foundPlayers[0].isWinner());
    assertEquals(fixture[0].getRapidClickCount(), foundPlayers[0].getRapidClickCount());
    assertEquals(fixture[0].getRightClickCount(), foundPlayers[0].getRightClickCount());
  }

  @Test
  void update() throws DAOException {
    // Create a new player
    dao.create(fixture[0]);
    Player player = dao.findAll()[0];

    // Update the player
    player.setClickCount(5);
    player.setScore(4);
    player.setWinner();
    player.setRapidClickCount(3);

    dao.update(player);

    // Find the player by its id
    Player foundPlayer = dao.findById(player.getId().intValue());
    assertEquals(player, foundPlayer);

  }

  @Test
  void delete() throws DAOException {
    // Create a new player
    dao.create(fixture[0]);
    Player player = dao.findAll()[0];

    // Delete the player
    dao.delete(player);

    // Find all the players
    Player[] foundPlayers = dao.findAll();
    assertEquals(0, foundPlayers.length);
  }
}
