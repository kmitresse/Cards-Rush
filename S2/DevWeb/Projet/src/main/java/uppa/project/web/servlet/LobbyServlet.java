/*
 * MainMenuServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;
import uppa.project.database.pojo.User;

@WebServlet(name = "lobbyServlet", value = "/lobby")
public class LobbyServlet extends HttpServlet {

  public void init() {
  }

  /**
   * Affichage de la page de lobby
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   * @throws ServletException si une erreur de servlet survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/pages/lobby.jsp").forward(request, response);
  }

  public void destroy() {
  }
}
