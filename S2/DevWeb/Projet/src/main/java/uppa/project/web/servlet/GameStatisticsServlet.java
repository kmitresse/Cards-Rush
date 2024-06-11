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
import uppa.project.web.translation.Translator;

@WebServlet(name = "game-statistics", value = "/game-statistics")
public class GameStatisticsServlet extends HttpServlet {

  public void init() {
  }

  /**
   * Affichage de la page de statistiques de jeu
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   * @throws ServletException si une erreur de servlet survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (request.getSession().getAttribute("translator") == null) {
      request.getSession().setAttribute("language", "FR");
      request.getSession().setAttribute("translator", Translator.generateTranslator(request.getSession(), request.getServletContext()));
    }
    Game game;
    try {
      DAO<Game> gameDAO = new Game_JPA_DAO_Factory().getDAOGame();
      game = gameDAO.findById(Integer.parseInt(request.getParameter("id")));
      ArrayList<Player> players = new ArrayList<>(game.getPlayers());
      request.setAttribute("players", players);
      request.setAttribute("game", game);
      request.getRequestDispatcher("/WEB-INF/pages/game-statistics.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
