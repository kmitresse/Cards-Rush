package uppa.project.bean;

import java.io.Serializable;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

public class LoginBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;
  private User user;

  private HttpResponse error;

  public LoginBean() {
  }

  public LoginBean(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public boolean validate() {

    Game_JPA_DAO_Factory factory = new Game_JPA_DAO_Factory();

    try {
      DAO<User> userDao = factory.getDAOUser();
      User[] user = userDao.findByField("username", username);
      System.out.println(user.length);

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

  public LoginBean setUsername(String username) {
    this.username = username;
    return this;
  }

  public LoginBean setPassword(String password) {
    this.password = password;
    return this;
  }

  public User getUser() {
    return user;
  }

  public HttpResponse getError() {
    return error;
  }
}
