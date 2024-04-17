package uppa.project.dao.jpa;

import jakarta.persistence.EntityManager;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_User;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;
import uppa.project.database.dao.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

class DAO_JPA_UserTest {

  static EntityManager entityManager;
  static DAO<User> dao;
  static User[] fixture;

  @BeforeAll
  static void setUp() throws DAOException {
    EntityManagerProvider.setPersitenceUnitName("test");
    entityManager = EntityManagerProvider.getInstance();
    dao = new Game_JPA_DAO_Factory().getDAOUser();

    assertEquals(dao.getClass(), DAO_JPA_User.class);
  }

  @BeforeEach
  void clean() {
    fixture = new User[] {
      new User("username", "email", "password", new Date(), User.Gender.MALE),
      new User("username1", "email1", "password1", new Date(), User.Gender.FEMALE),
    };
    entityManager.getTransaction().begin();
  }

  @AfterEach
  void rollback() {
    entityManager.getTransaction().rollback();
  }

  @Test
  void findById() throws DAOException {
    // Create a new user
    dao.create(fixture[0]);
    User user = dao.findAll()[0];

    // Find the user by id
    User userInDb = dao.findById(user.getId().intValue());
    assertEquals(user.getId(), userInDb.getId());
  }

  @Test
  void findByField() throws DAOException {
    // Create users
    for (User user : fixture) dao.create(user);

    // Find the user by username
    User[] usersInDb = dao.findByField("username", fixture[0].getUsername());
    assertEquals(1, usersInDb.length);

    // Check if the user is the same
    assertEquals(fixture[0].getUsername(), usersInDb[0].getUsername());
    assertEquals(fixture[0].getEmail(), usersInDb[0].getEmail());
    assertEquals(fixture[0].getPassword(), usersInDb[0].getPassword());
  }

  @Test
  void findAll() throws DAOException {
    // Find all users
    User[] fixture = dao.findAll();
    assertEquals(0, fixture.length);

    // Create a new user
    for (User user : fixture) dao.create(user);

    // Find all users
    User[] usersInDb = dao.findAll();
    assertEquals(fixture.length, usersInDb.length);
  }

  @Test
  void create() throws DAOException {
    // Create users
    dao.create(fixture[0]);

    // Check if the user is in the database
    User[] usersInDb = dao.findAll();
    assertEquals(1, usersInDb.length);

    // Check if the user is the same
    assertEquals(fixture[0].getUsername(), usersInDb[0].getUsername());
    assertEquals(fixture[0].getEmail(), usersInDb[0].getEmail());
    assertEquals(fixture[0].getPassword(), usersInDb[0].getPassword());
    assertEquals(fixture[0].getBirth(), usersInDb[0].getBirth());
    assertEquals(fixture[0].getGender(), usersInDb[0].getGender());

  }

  @Test
  void update() throws DAOException {
    // Create a new user
    dao.create(fixture[0]);
    User user = dao.findAll()[0];

    // Update the user
    user.setUsername("new_username");
    dao.update(user);

    // Check if the user is in the database
    User[] usersInDb = dao.findAll();
    assertEquals(1, usersInDb.length);

    // Check if the user is the same
    assertEquals(user.getUsername(), usersInDb[0].getUsername());
  }

  @Test
  void delete() throws DAOException {
    // Create a new user
    dao.create(fixture[0]);
    User user = dao.findAll()[0];

    // Delete the user
    dao.delete(user);

    // Check if the user is not in the database
    assertNull(dao.findById(user.getId().intValue()));
  }
}
