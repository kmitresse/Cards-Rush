/*
 * MainMenuServlet.java, 20/03/2024
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
import java.util.ArrayList;
import java.util.List;
import uppa.project.pojo.Game;
import uppa.project.pojo.Player;
import uppa.project.pojo.User;

@WebServlet(name = "mainMenuServlet", value = "/main-menu")
public class MainMenuServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(request.getContextPath() + "/login");
      return;
    }
    manageNewGame(request, response, user);
    manageStatistiques(request, response, user);
    response.sendRedirect(request.getContextPath() + "/main-menu");
  }

  public void destroy() {
  }

  private void manageMainMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

  }

  private void manageNewGame(HttpServletRequest request, HttpServletResponse response, User sessionUser) throws IOException, ServletException {
    List<User> connectedUsers = new ArrayList<User>();
    /*TODO: récuperer la liste des joueurs connectés
         penser à retirer l'utilisateur principal de la liste*/
    connectedUsers.remove(sessionUser);
    request.setAttribute("connectedUsers", connectedUsers);
    request.getRequestDispatcher("/WEB-INF/views/new-game.jsp").forward(request, response);
  }

  private void manageStatistiques(HttpServletRequest request, HttpServletResponse response, User sessionUser) throws IOException, ServletException {
    List<Game> games = new ArrayList<Game>();
    for(Player player : sessionUser.getPlayedGames()) {
      Game game = player.getGame();
      game.sortPlayersByScore();
      games.add(game);
    }
    request.setAttribute("games", games);
  }
}
