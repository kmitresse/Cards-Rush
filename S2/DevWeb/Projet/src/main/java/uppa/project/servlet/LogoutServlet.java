/*
 * LogoutServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

  private final Gson gson = new Gson();

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getSession().getAttribute("user") != null) {
      request.getSession().removeAttribute("user");
    }

    response.sendRedirect(request.getContextPath() + "/login");
  }

  public void destroy() {
  }
}