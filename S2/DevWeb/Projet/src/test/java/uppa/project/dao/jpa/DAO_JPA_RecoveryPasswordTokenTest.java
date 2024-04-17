package uppa.project.dao.jpa;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_RecoveryPasswordToken;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;
import uppa.project.database.dao.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

class DAO_JPA_RecoveryPasswordTokenTest {

  static EntityManager entityManager;
  static DAO<RecoveryPasswordToken> dao;

  static RecoveryPasswordToken[] fixture;

  @BeforeAll
  static void setUp() throws DAOException {
    EntityManagerProvider.setPersitenceUnitName("test");
    entityManager = EntityManagerProvider.getInstance();
    dao = new Game_JPA_DAO_Factory().getDAORecoveryPasswordToken();

    assertEquals(dao.getClass(), DAO_JPA_RecoveryPasswordToken.class);
  }

  @BeforeEach
  void clean() {
    fixture = new RecoveryPasswordToken[] {
      new RecoveryPasswordToken(
        new BigDecimal(1),  "token1",
        new User(
        "user1", "email1", "password1",
          new Date(100, Calendar.JANUARY, 1),
          User.Gender.OTHER
        ),
        new Date(124, Calendar.FEBRUARY, 2)
      ),
      new RecoveryPasswordToken(
        new BigDecimal(2),  "token2",
        new User(
        "user2", "email2", "password2",
          new Date(100, Calendar.MARCH, 3),
          User.Gender.FEMALE
        ),
        new Date(124, Calendar.APRIL, 4)
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
    // Create a new recovery password token
    dao.create(fixture[0]);
    RecoveryPasswordToken recoveryPasswordToken = dao.findAll()[0];

    // Find the recovery password token by id
    RecoveryPasswordToken recoveryPasswordTokenInDb = dao.findById(recoveryPasswordToken.getId().intValue());
    assertEquals(recoveryPasswordToken.getId(), recoveryPasswordTokenInDb.getId());
  }

  @Test
  void findByField() throws DAOException {
    // Create recovery password tokens
    for (RecoveryPasswordToken recoveryPasswordToken : fixture)
      dao.create(recoveryPasswordToken);

    // Find the recovery password token by token
    RecoveryPasswordToken recoveryPasswordToken = dao.findByField("token", "token1")[0];
    assertEquals(fixture[0].getToken(), recoveryPasswordToken.getToken());

    // Check if the recovery password token is the same
    assertEquals(fixture[0].getToken(), recoveryPasswordToken.getToken());
  }

  @Test
  void findAll() throws DAOException {
    // Find all recovery password tokens
    RecoveryPasswordToken[] recoveryPasswordTokens = dao.findAll();
    assertEquals(0, recoveryPasswordTokens.length);

    // Create recovery password tokens
    for (RecoveryPasswordToken recoveryPasswordToken : fixture)
      dao.create(recoveryPasswordToken);

    // Find all recovery password tokens
    recoveryPasswordTokens = dao.findAll();
    assertEquals(fixture.length, recoveryPasswordTokens.length);
  }

  @Test
  void create() throws DAOException {
    // Create a new recovery password token
    dao.create(fixture[0]);
    RecoveryPasswordToken recoveryPasswordToken = dao.findAll()[0];

    // Check if the recovery password token is the same
    assertEquals(fixture[0].getToken(), recoveryPasswordToken.getToken());
    assertEquals(fixture[0].getExpirationDate(), recoveryPasswordToken.getExpirationDate());
  }

  @Test
  void update() throws DAOException {
    // Create a new recovery password token
    dao.create(fixture[0]);
    RecoveryPasswordToken recoveryPasswordToken = dao.findAll()[0];

    // Update the recovery password token
    recoveryPasswordToken.setToken("newToken");
    dao.update(recoveryPasswordToken);

    // Check if the recovery password token is in the database
    RecoveryPasswordToken recoveryPasswordTokenInDb = dao.findAll()[0];
    assertEquals(recoveryPasswordToken.getToken(), recoveryPasswordTokenInDb.getToken());

    // Check if the recovery password token is the same
    recoveryPasswordTokenInDb = dao.findAll()[0];
    assertEquals(recoveryPasswordToken.getToken(), recoveryPasswordTokenInDb.getToken());
  }

  @Test
  void delete() throws DAOException {
    // Create a new recovery password token
    dao.create(fixture[0]);
    RecoveryPasswordToken recoveryPasswordToken = dao.findAll()[0];

    // Delete the recovery password token
    dao.delete(recoveryPasswordToken);

    // Check if the recovery password token is not in the database
    assertNull(dao.findById(recoveryPasswordToken.getId().intValue()));
  }
}
