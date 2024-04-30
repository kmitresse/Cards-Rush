package uppa.project.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;

@WebServlet(name = "game-statistics", value = "/game-statistics")
public class GameStatisticsServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Game game;
    try {
      DAO<Game> gameDAO = new Game_JPA_DAO_Factory().getDAOGame();
      game = gameDAO.findById(Integer.parseInt(request.getParameter("id")));
      ArrayList<Player> players = new ArrayList<>();
      for (Player player : game.getPlayers()) players.add(player);
      request.setAttribute("players", players);

      System.out.println("GameStatisticsServlet.doGet() : game = " + game);
      System.out.println("GameStatisticsServlet.doGet() : players = " + players);

//      request.removeAttribute("id");
//      game.sortPlayersByScoreAndRapidity();
      request.setAttribute("game", game);
      request.getRequestDispatcher("/WEB-INF/pages/game-statistics.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
