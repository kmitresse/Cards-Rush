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
import uppa.project.pojo.User;
import uppa.project.provider.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

class DAO_JPA_GameTest {

  static EntityManager entityManager;
  static DAO<Game> dao;

  static Game[] fixture;

  @BeforeAll
  static void setUp() throws DAOException {
    EntityManagerProvider.setPersitenceUnitName("test");
    entityManager = EntityManagerProvider.getInstance();
    dao = new Game_JPA_DAO_Factory().getDAOGame();

    assertEquals(dao.getClass(), DAO_JPA_Game.class);
  }

  @BeforeEach
  void clean() {
    fixture = new Game[] {
      new Game(
        new BigDecimal(1),
        new Date(100, 10, 1),
        Game.Difficulty.EASY,
        4,
        2,
        4,
        new ArrayList<>()
      ),
      new Game(
        new BigDecimal(2),
        new Date(100, 12, 1),
        Game.Difficulty.HARD,
        3,
        2,
        4,
        new ArrayList<>()
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
    // Create the game
    dao.create(fixture[0]);

    // Retrieve the fixture
    Game game = dao.findById(fixture[0].getId().intValue());

    // Check if the retrieved fixture is the same as the created fixture
    assertEquals(game.getCreatedAt(), fixture[0].getCreatedAt());
    assertEquals(game.getDifficulty(), fixture[0].getDifficulty());
    assertEquals(game.getNbColors(), fixture[0].getNbColors());
    assertEquals(game.getNbValuesPerColor(), fixture[0].getNbValuesPerColor());
    assertEquals(game.getNbRounds(), fixture[0].getNbRounds());
  }

  @Test
  void findByField() throws DAOException {
    // Create games
    for (Game game : fixture) {
      dao.create(game);
      System.out.println(game.getNbRounds());
    }

    // Retrieve the fixture by nbRounds
    Game[] games = dao.findByField("nbRounds", 4);
    assertEquals(1, games.length);

    // Check if the retrieved fixture is the same as the created fixture
    assertEquals(games[0].getCreatedAt(), fixture[0].getCreatedAt());
    assertEquals(games[0].getDifficulty(), fixture[0].getDifficulty());
    assertEquals(games[0].getNbColors(), fixture[0].getNbColors());
    assertEquals(games[0].getNbValuesPerColor(), fixture[0].getNbValuesPerColor());
    assertEquals(games[0].getNbRounds(), fixture[0].getNbRounds());
  }

  @Test
  void findAll() throws DAOException {
    // Create games
    for (Game game : fixture) {
      dao.create(game);
    }

    // Retrieve all games
    Game[] games = dao.findAll();
    assertEquals(fixture.length, games.length);
  }

  @Test
  void create() throws DAOException {
    // Create the game
    dao.create(fixture[0]);

    // Retrieve the fixture
    Game game = dao.findById(fixture[0].getId().intValue());

    // Check if the retrieved fixture is the same as the created fixture
    assertEquals(game.getCreatedAt(), fixture[0].getCreatedAt());
    assertEquals(game.getDifficulty(), fixture[0].getDifficulty());
    assertEquals(game.getNbColors(), fixture[0].getNbColors());
    assertEquals(game.getNbValuesPerColor(), fixture[0].getNbValuesPerColor());
    assertEquals(game.getNbRounds(), fixture[0].getNbRounds());
  }

  @Test
  void update() throws DAOException {
    // Create the game
    dao.create(fixture[0]);

    // Update the game
    fixture[0].setNbColors(4);
    dao.update(fixture[0]);

    // Retrieve the fixture
    Game game = dao.findById(fixture[0].getId().intValue());

    // Check if the retrieved fixture is the same as the updated fixture
    assertEquals(4, game.getNbColors());
  }

  @Test
  void delete() throws DAOException {
    // Create the game
    dao.create(fixture[0]);
    Game game = dao.findAll()[0];

    // Delete the game
    dao.delete(game);

    // Check if the game has been deleted
    assertNull(dao.findById(game.getId().intValue()));
  }
}
