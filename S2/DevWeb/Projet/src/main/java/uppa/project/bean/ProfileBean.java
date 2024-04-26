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
  private String oldGender;
  private String gender;
  private User user;
  private HttpResponse error;

  public ProfileBean() {}

  public ProfileBean(String username,String oldEmail, String email, String oldPassword, String password, String gender) {
    this.id = username;
    this.oldEmail = oldEmail;
    this.email = email;
    this.oldPassword = oldPassword;
    this.password = password;
    this.gender = gender;
  }

  public boolean validate() {
    EntityManager entityManager = EntityManagerProvider.getInstance();
    entityManager.getTransaction().begin();
    try{
      DAO<User> userDAO = new Game_JPA_DAO_Factory().getDAOUser();
      //Check if the user is valid
      user = userDAO.findById(Integer.parseInt(id));
      if (user == null) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Utilisateur non trouvé");
        return false;
      }
      //Check if the email is not already taken
      User[] users = userDAO.findByField("email", email);
      if (!oldEmail.equals(email) && users.length > 0) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Cet email est déjà utilisé");
        return false;
      }
      //Check if the old password is correct
      if (!oldPassword.equals("") && user.verifyPassword(oldPassword) == false) {
        error = new HttpResponse(HttpResponseCode.UNAUTHORIZED, "Ancien mot de passe incorrect");
        return false;
      }
      //Update the user
      user.setEmail(email);
      user.setPassword(password);
      user.setGender(User.Gender.valueOf(gender));
      userDAO.update(user);
      entityManager.getTransaction().commit();
      return true;
    } catch (DAOException e) {
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, "Une erreur est survenue (DB_CONNECTION_ERROR:002)");
      entityManager.getTransaction().rollback();
      return false;
    }
  }

  public ProfileBean setId(String id) {
    this.id = id;
    return this;
  }

  public ProfileBean setOldEmail(String email) {
    this.oldEmail = email;
    return this;
  }
  public ProfileBean setEmail(String email) {
    this.email = email;
    return this;
  }

  public ProfileBean setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
    return this;
  }

  public ProfileBean setPassword(String password) {
    this.password = password;
    return this;
  }

  public ProfileBean setGender(String gender) {
    this.gender = gender;
    return this;
  }

  public HttpResponse getError() {
    return error;
  }

  public User getUser() {
    return user;
  }
}
