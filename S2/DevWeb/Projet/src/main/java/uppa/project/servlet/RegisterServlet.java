/*
 * RegisterServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import uppa.project.dao.DAOException;
import uppa.project.dao.jpa.DAO_JPA_User;
import uppa.project.pojo.User;
import uppa.project.provider.EntityManagerProvider;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

  static final EntityManager em = EntityManagerProvider.getInstance();
  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (request.getSession().getAttribute("user") != null) {
      response.sendRedirect(request.getContextPath() + "/main-menu");
      return;
    }

    request.setAttribute("current", "register");
    request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
  }

  public void destroy() {
  }
}
