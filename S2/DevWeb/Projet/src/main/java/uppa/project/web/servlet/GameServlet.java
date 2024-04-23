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
import java.io.IOException;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_Game;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Game;

@WebServlet(name = "gameServlet", value = "/game")
public class GameServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      DAO<Game> gameDAO = new Game_JPA_DAO_Factory().getDAOGame();
      Game game = gameDAO.findById(Integer.parseInt(request.getParameter("id")));
      request.setAttribute("game", game);

      request.getRequestDispatcher("/WEB-INF/pages/game.jsp").forward(request, response);
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  public void destroy() {
  }
}
