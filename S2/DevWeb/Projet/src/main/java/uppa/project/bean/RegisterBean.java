package uppa.project.bean;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;

public class RegisterBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String email;
  private String password;
  private String birth;
  private String gender;
  private String errorMessage;

  public RegisterBean() {
  }

  public RegisterBean(String username, String email, String password, String birth, String gender) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.birth = birth;
    this.gender = gender;
  }

  public boolean validate() {
    Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
    DAO<User> userDAO;

    // Check if the user is valid
    try {
      userDAO = jpaDaoFactory.getDAOUser();

      // Check if the username is already taken
      User[] users = userDAO.findByField("username", username);
      if (users.length > 0) {
        errorMessage = "Ce nom d'utilisateur est déjà pris";
        return false;
      }

      // Check if the email is already taken
      users = userDAO.findByField("email", email);
      if (users.length > 0) {
        errorMessage = "Cet email est déjà utilisé";
        return false;
      }
    } catch(DAOException e) {
      errorMessage = "Une erreur est survenue (DB_CONNECTION_ERROR)";
      return false;
    }

    // Create a new User
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    try {
      user.setGender(User.Gender.valueOf(gender));
    } catch (IllegalArgumentException e) {
      errorMessage = "Le genre n'est pas valide";
      return false;
    }

    // convert the birthdate from String as YYYY-MM-DD to Date object
    try {
      LocalDate localDate = LocalDate.parse(birth);
      ZoneId defaultZoneId = ZoneId.systemDefault();
      Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
      user.setBirth(date);
    } catch (Exception e) {
      errorMessage = "La date de naissance n'est pas valide";
      return false;
    }

    // Send the user to the database
    EntityManager entityManager = EntityManagerProvider.getInstance();
    entityManager.getTransaction().begin();
    try {
      userDAO = jpaDaoFactory.getDAOUser();
      userDAO.create(user);
      entityManager.getTransaction().commit();
      return true;
    } catch(DAOException e) {
      entityManager.getTransaction().rollback();
    }
    return false;
  }

  public RegisterBean setUsername(String username) {
    this.username = username;
    return this;
  }

  public RegisterBean setPassword(String password) {
    this.password = password;
    return this;
  }

  public RegisterBean setEmail(String email) {
    this.email = email;
    return this;
  }

  public RegisterBean setBirth(String birth) {
    this.birth = birth;
    return this;
  }

  public RegisterBean setGender(String gender) {
    this.gender = gender;
    return this;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
