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
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;

public class ResetPasswordBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String token;
  private String password;
  private String errorMessage;

  public ResetPasswordBean() {
  }

  public ResetPasswordBean(String token, String password) {
    this.token = token;
    this.password = password;
  }

  public boolean validate() {
    Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
    DAO<User> userDAO;
    DAO<RecoveryPasswordToken> recoveryPasswordTokenDAO;

    // Check if the user is valid
    EntityManager entityManager = EntityManagerProvider.getInstance();
    entityManager.getTransaction().begin();
    try {
      userDAO = jpaDaoFactory.getDAOUser();
      recoveryPasswordTokenDAO = jpaDaoFactory.getDAORecoveryPasswordToken();

      // Check if the token is valid
      RecoveryPasswordToken[] tokens = recoveryPasswordTokenDAO.findByField("token", token);
      if (tokens.length == 0) {
        errorMessage = "Ce token n'est pas valide";
        return false;
      }
      RecoveryPasswordToken token = tokens[0];

      //Recuperer l'utilisateur associé au token
      User user = token.getUser();
      if (user == null) {
        errorMessage = "Erreur: Aucun utilisateur associé à ce token";
        return false;
      }
      user.setPassword(password);

      userDAO.update(user);
      entityManager.getTransaction().commit();
    } catch (DAOException e) {
      errorMessage = "Une erreur est survenue (DB_CONNECTION_ERROR)";
      entityManager.getTransaction().rollback();
      return false;
    }
    return true;
  }

  public ResetPasswordBean setToken(String token) {
    this.token = token;
    return this;
  }

  public ResetPasswordBean setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
