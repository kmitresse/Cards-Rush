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

public class ProfileBean {
  private String id;
  private String oldEmail;
  private String email;
  private String oldPassword;
  private String password;
  private String gender;
  private User user;
  private HttpResponse error;

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
      //Check if the user is valid
      user = userDAO.findById(Integer.parseInt(id));
      if (user == null) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Utilisateur non trouvé");
        entityManager.getTransaction().rollback();
        return false;
      }
      //Check if the email is not already taken
      User[] users = userDAO.findByField("email", email);
      if (!oldEmail.equals(email) && users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Cet email est déjà utilisé");
        entityManager.getTransaction().rollback();
        return false;
      }
      //Check if the oldPassword is correct
      if(!oldPassword.isEmpty() && !user.verifyPassword(oldPassword)) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Ancien mot de passe incorrect");
        entityManager.getTransaction().rollback();
        return false;
      }
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, "Une erreur est survenue (DB_CONNECTION_ERROR:002)");
      entityManager.getTransaction().rollback();
      return false;
    }
    //Update the user
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
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, "Une erreur est survenue (DB_CONNECTION_ERROR:002)");
      entityManager.getTransaction().rollback();
      return false;
    }
  }

  /**
   *
   * @param id l'identifiant de l'utilisateur
   * @return l'entité
   */
  public ProfileBean setId(String id) {
    this.id = id;
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
