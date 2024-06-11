/*
 * ProfileBean.java, 01/05/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */
package uppa.project.bean;

import jakarta.persistence.EntityManager;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;
import uppa.project.web.translation.Translator;

public class ProfileBean {
  private String username;
  private String oldEmail;
  private String email;
  private String oldPassword;
  private String password;
  private String gender;
  private User user;
  private HttpResponse error;
  private Translator translator;

  public ProfileBean() {}

/**
   * Validation des paramètres de la requête & mise à jour des informations de l'utilisateur
   *
   * @return true si les paramètres sont valides, false sinon
   */
  public boolean validate() {
    EntityManager entityManager = EntityManagerProvider.getInstance();
    entityManager.getTransaction().begin();
    DAO<User> userDAO;
    try {
       userDAO= new Game_JPA_DAO_Factory().getDAOUser();
      // Vérification de l'existence de l'utilisateur
      user = (userDAO.findByField("username",username).length == 0 ? null : userDAO.findByField("username",username)[0]);
      if (user == null) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("profile_error_username_unknown"));
        entityManager.getTransaction().rollback();
        return false;
      }
      // Vérification de l'unicité de l'adresse e-mail
      User[] users = userDAO.findByField("email", email);
      if (!oldEmail.equals(email) && users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("profile_error_email"));
        entityManager.getTransaction().rollback();
        return false;
      }
      // Verification de l'ancien mot de passe
      if(!oldPassword.isEmpty() && !user.verifyPassword(oldPassword)) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, translator.translate("profile_error_old_password"));
        entityManager.getTransaction().rollback();
        return false;
      }
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, translator.translate("internal_error_2"));
      entityManager.getTransaction().rollback();
      return false;
    }
    // Mise à jour des informations de l'utilisateur
    user.setEmail(email);
    if (!password.isEmpty()) {
      user.setPassword(password);
    }
    user.setGender(User.Gender.valueOf(gender));
    try {
    userDAO.update(user);
    entityManager.getTransaction().commit();
    return true;
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, translator.translate("internal_error_2"));
      entityManager.getTransaction().rollback();
      return false;
    }
  }

  /**
   *
   * @param username le pseudo de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setUsername(String username) {
    this.username = username;
    return this;
  }

  /**
   *
   * @param email l'adresse e-mail actuelle de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setOldEmail(String email) {
    this.oldEmail = email;
    return this;
  }

  /**
   *
   * @param email la nouvelle adresse e-mail de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setEmail(String email) {
    this.email = email;
    return this;
  }

  /**
   *
   * @param oldPassword l'ancien mot de passe de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
    return this;
  }

  /**
   *
   * @param password le nouveau mot de passe de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setPassword(String password) {
    this.password = password;
    return this;
  }

  /**
   *
   * @param gender le genre de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setGender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   *
   * @param translator le traducteur
   * @return l'entité
   */
  public ProfileBean setTranslator(Translator translator) {
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

  /**
   *
   * @return l'utilisateur
   */
  public User getUser() {
    return user;
  }
}
