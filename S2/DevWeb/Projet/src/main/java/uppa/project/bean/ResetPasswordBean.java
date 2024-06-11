package uppa.project.bean;

import jakarta.persistence.EntityManager;
import java.io.Serial;
import java.io.Serializable;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;
import uppa.project.web.translation.Translator;

public class ResetPasswordBean implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String token;
  private String password;
  private String errorMessage;
  private Translator translator;

  public ResetPasswordBean() {
  }

  /**
   * Validation des paramètres de la requête & gestion de la réinitialisation du mot de passe
   *
   * @return true si le token est valide, false sinon
   */
  public boolean validate() {
    Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
    DAO<User> userDAO;
    DAO<RecoveryPasswordToken> recoveryPasswordTokenDAO;

    // Vérification du lien entre le token et un utilisateur
    EntityManager entityManager = EntityManagerProvider.getInstance();
    entityManager.getTransaction().begin();
    try {
      userDAO = jpaDaoFactory.getDAOUser();
      recoveryPasswordTokenDAO = jpaDaoFactory.getDAORecoveryPasswordToken();

      // Vériier l'existence du token
      RecoveryPasswordToken[] tokens = recoveryPasswordTokenDAO.findByField("token", token);
      if (tokens.length == 0) {
        errorMessage = "Ce token n'est pas valide";
        return false;
      }
      RecoveryPasswordToken token = tokens[0];

      // Récupéreration de l'utilisateur associé au token
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

  /**
   *
   * @param token le token de réinitialisation de mot de passe
   * @return this
   */
  public ResetPasswordBean setToken(String token) {
    this.token = token;
    return this;
  }

  /**
   *
   * @param password le nouveau mot de passe
   * @return this
   */
  public ResetPasswordBean setPassword(String password) {
    this.password = password;
    return this;
  }

  /**
   *
   * @param translator le traducteur
   * @return this
   */
  public ResetPasswordBean setTranslator(Translator translator) {
    this.translator = translator;
    return this;
  }

  /**
   *
   * @return le message d'erreur
   */
  public String getErrorMessage() {
    return errorMessage;
  }
}
