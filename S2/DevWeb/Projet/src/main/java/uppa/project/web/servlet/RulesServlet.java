package uppa.project.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "rulesServlet", value = "/rules")
public class RulesServlet extends HttpServlet {
  public void init() {
  }

  /**
   * Affichage de la page des règles
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   * @throws ServletException si une erreur de servlet survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/pages/rules.jsp").forward(request, response);
  }
  public void destroy() {
  }
}
