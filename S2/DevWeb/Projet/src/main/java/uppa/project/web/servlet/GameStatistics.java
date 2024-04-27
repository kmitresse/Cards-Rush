package uppa.project.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Game;

@WebServlet(name = "game-statistics", value = "/game-statistics")
public class GameStatistics extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Game game;
    try {
      DAO<Game> gameDAO = new Game_JPA_DAO_Factory().getDAOGame();
      game = gameDAO.findById(Integer.parseInt(request.getParameter("id")));
      request.removeAttribute("id");
      game.sortPlayersByScore();
      request.setAttribute("game", game);
      request.getRequestDispatcher("/WEB-INF/pages/game-statistics.jsp").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
