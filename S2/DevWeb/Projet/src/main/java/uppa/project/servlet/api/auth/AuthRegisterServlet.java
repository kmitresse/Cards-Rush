/*
 * RegisterApiServlet.java, 03/04/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet.api.auth;

import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.pojo.User;
import uppa.project.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.provider.EntityManagerProvider;

@WebServlet(name = "authRegisterServlet", value = "/api/auth/register")
public class AuthRegisterServlet extends HttpServlet {

  private class JsonRequest {
    private String username;
    private String password;
    private String email;
    private String birthdate;

    private String gender;
  }

  private final Gson gson = new Gson();

  public void init() {}

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    // Get the json in the request body
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while((line = reader.readLine()) != null) {
      sb.append(line);
    }
    String requestBody = sb.toString();
    JsonRequest jsonRequest = gson.fromJson(requestBody, JsonRequest.class);

    // Create the user
    User user = this.createUser(jsonRequest);
    if (user == null) {
      // TODO send error
      return;
    }

    out.println(gson.toJson(user));
    out.flush();
  }

  private User createUser(JsonRequest jsonRequest) {

    // Create a new User
    User user = new User();
    user.setUsername(jsonRequest.username);
    user.setPassword(jsonRequest.password);
    user.setEmail(jsonRequest.email);
    user.setGender(User.Gender.valueOf(jsonRequest.gender));

    // convert the birthdate from String as YYYY-MM-DD to Date object)
    LocalDate localDate = LocalDate.parse(jsonRequest.birthdate);
    ZoneId defaultZoneId = ZoneId.systemDefault();
    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    user.setBirth(date);

    // Send the user to the database
    EntityManager entityManager = EntityManagerProvider.getInstance();

    entityManager.getTransaction().begin();
    try {
      Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
      DAO<User> userDAO = jpaDaoFactory.getDAOUser();

      User newUser = userDAO.create(user);
      entityManager.getTransaction().commit();
      return newUser;
    } catch(DAOException e) {
      entityManager.getTransaction().rollback();
      return null;
    }
  }

  public void destroy() {
  }
}
