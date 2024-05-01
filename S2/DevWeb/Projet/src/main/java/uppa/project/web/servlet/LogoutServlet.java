/*
 * LogoutServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

  public void init() {
  }

  /**
   * Déconnexion de l'utilisateur
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.getSession().removeAttribute("user");
    response.sendRedirect(request.getContextPath() + "/login");
  }

  public void destroy() {
  }
}
