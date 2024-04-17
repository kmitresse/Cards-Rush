package uppa.project.bean;

import java.io.Serializable;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;

public class LoginBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;
  private User user;

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

      for (User u : user) {
        if (u.getUsername().equals(username)) {
          this.user = u;
          return true;
        }
      }

    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  public User getUser() {
    return user;
  }

  public LoginBean setUsername(String username) {
    this.username = username;
    return this;
  }

  public LoginBean setPassword(String password) {
    this.password = password;
    return this;
  }
}
