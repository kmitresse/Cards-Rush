/*
 * LoginBean.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.bean;

import java.io.Serial;
import java.io.Serializable;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

public class LoginBean implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;
  private User user;
  private HttpResponse error;
  public LoginBean() {
  }

  /**
   * Validation des paramètres de la requête
   *
   * @return true si le nom d'utilisateur et le mot de passe sont valides, false sinon
   */
  public boolean validate() {

    Game_JPA_DAO_Factory factory = new Game_JPA_DAO_Factory();

    try {
      DAO<User> userDao = factory.getDAOUser();
      User[] user = userDao.findByField("username", username);

      for (User u : user) {
        if (u.getUsername().equals(username) && u.verifyPassword(password)) {
          this.user = u;
          return true;
        }
      }
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR,"Une erreur est survenue (DB_CONNECTION_ERROR:001)");
      return false;
    }

    error = new HttpResponse(HttpResponseCode.NOT_FOUND,"Le nom d'utilisateur ou le mot de passe est incorrect.");
    return false;
  }

  /**
   *
   * @param username le nom d'utilisateur supposé de l'utilisateur qui souhaite se connecter
   * @return l'entité
   */
  public LoginBean setUsername(String username) {
    this.username = username;
    return this;
  }

  /**
   *
   * @param password le mot de passe supposé de l'utilisateur qui souhaite se connecter
   * @return l'entité
   */
  public LoginBean setPassword(String password) {
    this.password = password;
    return this;
  }

  /**
   *
   * @return l'utilisateur connecté
   */
  public User getUser() {
    return user;
  }

  /**
   *
   * @return l'erreur
   */
  public HttpResponse getError() {
    return error;
  }
}
