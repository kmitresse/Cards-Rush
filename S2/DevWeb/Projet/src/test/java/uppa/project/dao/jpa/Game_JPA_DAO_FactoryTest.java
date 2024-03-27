package uppa.project.dao.jpa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.pojo.User;
import uppa.project.provider.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

class Game_JPA_DAO_FactoryTest {

  static Game_JPA_DAO_Factory factory;

  @BeforeAll
  static void setUp() {
    EntityManagerProvider.setPersitenceUnitName("test");
    factory = new Game_JPA_DAO_Factory();
  }

  @Test
  void test_getDAOUser() throws DAOException {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(() -> factory.getDAOUser());

    // Check if the returned object is a DAO<User>
    assertNotNull(factory.getDAOUser());

    // Check if the object is unique
    DAO<User> dao1 = factory.getDAOUser();
    DAO<User> dao2 = factory.getDAOUser();
    assertEquals(dao1, dao2);
  }

  @Test
  void test_getDAOGame() throws DAOException {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(() -> factory.getDAOGame());

    // Check if the returned object is a DAO<Game>
    assertNotNull(factory.getDAOGame());

    // Check if the object is unique
    DAO<User> dao1 = factory.getDAOUser();
    DAO<User> dao2 = factory.getDAOUser();
    assertEquals(dao1, dao2);
  }

  @Test
  void test_getDAOPlayer() throws DAOException {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(() -> factory.getDAOPlayer());

    // Check if the returned object is a DAO<Player>
    assertNotNull(factory.getDAOPlayer());

    // Check if the object is unique
    DAO<User> dao1 = factory.getDAOUser();
    DAO<User> dao2 = factory.getDAOUser();
    assertEquals(dao1, dao2);
  }

  @Test
  void test_getDAORecoveryPasswordToken() throws DAOException {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(() -> factory.getDAORecoveryPasswordToken());

    // Check if the returned object is a DAO<RecoveryPasswordToken>
    assertNotNull(factory.getDAORecoveryPasswordToken());

    // Check if the object is unique
    DAO<User> dao1 = factory.getDAOUser();
    DAO<User> dao2 = factory.getDAOUser();
    assertEquals(dao1, dao2);
  }
}
