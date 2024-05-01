package uppa.project.bean;

import jakarta.persistence.EntityManager;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

public class RegisterBean implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String username;
  private String email;
  private String password;
  private String birth;
  private String gender;

  private HttpResponse error;

  public RegisterBean() {
  }

  /**
   * Validation des paramètres de la requête & création d'un nouvel utilisateur
   *
   * @return true si les paramètres sont valides, false sinon
   */
  public boolean validate() {
    Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
    DAO<User> userDAO;

    // Check if the user is valid
    try {
      userDAO = jpaDaoFactory.getDAOUser();

      // Check if the username is already taken
      User[] users = userDAO.findByField("username", username);
      if (users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Ce nom d'utilisateur est déjà pris");
        return false;
      }

      // Check if the email is already taken
      users = userDAO.findByField("email", email);
      if (users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Cet email est déjà utilisé");
        return false;
      }
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, "Une erreur est survenue (DB_CONNECTION_ERROR:001)");
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
      error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Le genre n'est pas valide");
      return false;
    }

    // convert the birthdate from String as YYYY-MM-DD to Date object
    try {
      LocalDate localDate = LocalDate.parse(birth);
      ZoneId defaultZoneId = ZoneId.systemDefault();
      Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
      user.setBirth(date);
    } catch (Exception e) {
      error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "La date de naissance n'est pas valide");
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
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, "Une erreur est survenue (DB_CONNECTION_ERROR:002)");
      entityManager.getTransaction().rollback();
    }
    return false;
  }

  /**
   *
   * @param username le nom d'utilisateur
   * @return l'entité
   */
  public RegisterBean setUsername(String username) {
    this.username = username;
    return this;
  }

  /**
   *
   * @param password le mot de passe
   * @return l'entité
   */
  public RegisterBean setPassword(String password) {
    this.password = password;
    return this;
  }

  /**
   *
   * @param email l'adresse e-mail
   * @return l'entité
   */
  public RegisterBean setEmail(String email) {
    this.email = email;
    return this;
  }

  /**
   *
   * @param birth la date de naissance
   * @return l'entité
   */
  public RegisterBean setBirth(String birth) {
    this.birth = birth;
    return this;
  }

  /**
   *
   * @param gender le genre de l'utilisateur
   * @return l'entité
   */
  public RegisterBean setGender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   *
   * @return l'erreur
   */
  public HttpResponse getError() {
    return error;
  }
}
