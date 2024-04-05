/*
 * LoginServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "indexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (request.getSession().getAttribute("user") != null) {
      response.sendRedirect(request.getContextPath() + "/main-menu");
      return;
    }

    request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
  }

  public void destroy() {
  }
}
