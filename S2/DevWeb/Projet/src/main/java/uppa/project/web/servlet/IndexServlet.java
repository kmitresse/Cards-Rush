/*
 * LoginServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

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

  /**
   * Affichage de la page d'accueil
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   * @throws ServletException si une erreur de servlet survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setAttribute("current", "index");
    request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
  }

  public void destroy() {
  }
}
