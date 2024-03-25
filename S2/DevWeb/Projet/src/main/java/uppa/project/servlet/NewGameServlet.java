/*
 * NewGameServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import uppa.project.pojo.User;


@WebServlet(name = "newGameServlet", value = "/new-game")
public class NewGameServlet extends HttpServlet {
  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    if (request.getSession().getAttribute("user") == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    User[] users = new User[0];
    /*TODO: récuperer la liste des joueurs connectés
         penser à retirer l'utilisateur principal de la liste*/

    request.setAttribute("connectedUsers", users);
    request.getRequestDispatcher("/WEB-INF/views/new-game.jsp").forward(request, response);
  }

  public void destroy() {
  }
}
