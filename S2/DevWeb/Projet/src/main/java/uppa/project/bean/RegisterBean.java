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
import uppa.project.web.translation.Translator;

public class RegisterBean implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String username;
  private String email;
  private String password;
  private String birth;
  private String gender;

  private HttpResponse error;
  private Translator translator;

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

    // Vérification de l'unicité du nom d'utilisateur et de l'adresse e-mail
    try {
      userDAO = jpaDaoFactory.getDAOUser();

      // Vérification de l'unicité du nom d'utilisateur
      User[] users = userDAO.findByField("username", username);
      if (users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("register_error_username"));
        return false;
      }

      // Vérification de l'unicité de l'adresse e-mail
      users = userDAO.findByField("email", email);
      if (users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("register_error_email"));
        return false;
      }
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, translator.translate("internal_error_1"));
      return false;
    }

    // Creation de l'utilisateur
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    try {
      user.setGender(User.Gender.valueOf(gender));
    } catch (IllegalArgumentException e) {
      error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("register_error_gender"));
      return false;
    }

    // Convertion de la date de naissance de String au format YYYY-MM-DD en objet Date
    try {
      LocalDate localDate = LocalDate.parse(birth);
      ZoneId defaultZoneId = ZoneId.systemDefault();
      Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
      user.setBirth(date);
    } catch (Exception e) {
      error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("register_error_birthdate"));
      return false;
    }

    // Sauvegarde de l'utilisateur en base de données
    EntityManager entityManager = EntityManagerProvider.getInstance();
    entityManager.getTransaction().begin();
    try {
      userDAO = jpaDaoFactory.getDAOUser();
      userDAO.create(user);
      entityManager.getTransaction().commit();
      return true;
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, translator.translate("internal_error_2"));
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
   * @param translator le traducteur
   * @return l'entité
   */
  public RegisterBean setTranslator(Translator translator) {
    this.translator = translator;
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
