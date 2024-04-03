/*
 * LoginServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet.api.auth;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import uppa.project.pojo.User;
import uppa.project.pojo.json.ErrorResponse;
import uppa.project.pojo.json.LoginRequest;
import uppa.project.pojo.json.LoginResponse;
import uppa.project.utils.HttpRequestUtils;

@WebServlet(name = "authLoginServlet", value = "/api/auth/login")
public class AuthLoginServlet extends HttpServlet {

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
    LoginRequest loginRequest = gson.fromJson(requestBody, LoginRequest.class);

    // Check if the username and password are present
    if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
      int STATUS = 400;
      ErrorResponse error = new ErrorResponse(STATUS, "Bad Request", "Username and password are required");
      response.setStatus(STATUS);
      out.println(gson.toJson(error));
      return;
    }

    // Get User from database matching the username and password
    User user = AuthLoginServlet.loginUser(loginRequest);
    if (user == null) {
      int STATUS = 401;
      ErrorResponse error = new ErrorResponse(STATUS, "Unauthorized", "Invalid username or password");

      response.setStatus(STATUS);
      out.println(gson.toJson(error));
      return;
    }

    // Set the user in the session
    request.getSession().setAttribute("user", user);

    // Return the user as JSON
    LoginResponse loginResponse = new LoginResponse(200, user, request.getContextPath() + "/main-menu");
    String json = gson.toJson(loginResponse);
    out.println(json);
    out.flush();
  }

  private static User loginUser(LoginRequest loginRequest) {
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();

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
