/*
 * LoginServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet.api.auth;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.listener.SessionServletContextListener;
import uppa.project.pojo.User;

@WebServlet(name = "authLoginServlet", value = "/api/auth/login")
public class AuthLoginServlet extends HttpServlet {

  private static class JsonRequest {
    private String username;
    private String password;
  }

  private final Gson gson = new Gson();

  public void init() {
  }

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

    // Login User
    User user = loginUser(jsonRequest);

    // Set user in session
    request.getSession().setAttribute("user", user);
    SessionServletContextListener.addSession(request.getSession());

    out.println(gson.toJson(user));
    out.flush();
  }

  private static User loginUser(JsonRequest jsonRequest) {
    String username = jsonRequest.username;
    String password = jsonRequest.password;

    Game_JPA_DAO_Factory factory = new Game_JPA_DAO_Factory();
    try {
      DAO<User> userDao = factory.getDAOUser();
      User[] users = userDao.findByField("username", username);

      for (User user : users) {
        if (user.verifyPassword(password)) return user;
      }
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public void destroy() {
  }
}
