/*
 * LoginServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.servlet.json.ErrorApi;
import uppa.project.pojo.User;
import uppa.project.servlet.utils.HttpRequestUtils;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

  private final Gson gson = new Gson();

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (request.getSession().getAttribute("user") != null) {
      response.sendRedirect(request.getContextPath() + "/main-menu");
      return;
    }

    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    // Convert the string to a JSON object
    JsonObject jsonBody;
    try {
      String requestBody = HttpRequestUtils.getRequestBody(request);
      jsonBody = JsonParser.parseString(requestBody).getAsJsonObject();
    } catch (Exception e) {
      int STATUS = 400;

      ErrorApi error = new ErrorApi(STATUS, "Bad Request", "Invalid JSON");
      response.setStatus(STATUS);
      out.println(error.toJson());
      return;
    }

    // Check if the username and password are present
    JsonElement username = jsonBody.get("username");
    JsonElement password = jsonBody.get("password");
    if (username == null || password == null) {
      int STATUS = 400;

      ErrorApi error = new ErrorApi(STATUS, "Bad Request", "Username and password are required");
      response.setStatus(STATUS);
      out.println(error.toJson());
      return;
    }

    // Get User from database matching the username and password
    User user = LoginServlet.loginUser(username.getAsString(), password.getAsString());
    if (user == null) {
      int STATUS = 401;
      ErrorApi error = new ErrorApi(STATUS, "Unauthorized", "Invalid username or password");

      response.setStatus(STATUS);
      out.println(error.toJson());
      return;
    }

    // Return the user as JSON
    String json = gson.toJson(user);
    out.println(json);
    out.flush();
  }

  private static User loginUser(String username, String password) {
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