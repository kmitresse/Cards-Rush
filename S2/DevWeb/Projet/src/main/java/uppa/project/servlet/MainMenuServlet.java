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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import uppa.project.pojo.Game;
import uppa.project.pojo.Player;
import uppa.project.pojo.User;

@WebServlet(name = "mainMenuServlet", value = "/main-menu")
public class MainMenuServlet extends HttpServlet {

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    User user = (User) request.getSession().getAttribute("user");
//    if (user == null) {
//      response.sendRedirect(request.getContextPath() + "/login");
//      return;
//    }
    manageNewGame(request, response, user);
    //manageStatistiques(request, response, user);
    request.getRequestDispatcher("/WEB-INF/views/main-menu.jsp").forward(request, response);
  }

  public void destroy() {
  }

  private void manageNewGame(HttpServletRequest request, HttpServletResponse response, User sessionUser) throws IOException, ServletException {
    List<User> connectedUsers = new ArrayList<User>();
    Set<HttpSession> loginsSessions = (Set<HttpSession>) request.getServletContext().getAttribute("loginSession");
    if (loginsSessions == null) {
      throw new RuntimeException("No login sessions found");
    }
    for(HttpSession session : loginsSessions) {
      User connectedUser = (User) session.getAttribute("user");
      if(session.getServletContext().getContextPath().equals(request.getServletContext().getContextPath()) && connectedUser != null && !connectedUser.equals(sessionUser)) {
        connectedUsers.add(connectedUser);
      }
    }
    connectedUsers.remove(sessionUser);
    request.setAttribute("connectedUsers", connectedUsers);
  }

  private void manageStatistiques(HttpServletRequest request, HttpServletResponse response, User sessionUser) throws IOException, ServletException {
    List<Game> games = new ArrayList<Game>();
    System.out.println(sessionUser.toString());
    System.out.println(sessionUser.getPlayedGames().size());
    if (sessionUser.getPlayedGames() != null) {
      for (Player player : sessionUser.getPlayedGames()) {
        Game game = player.getGame();
        game.sortPlayersByScore();
        games.add(game);
      }
    }
    request.setAttribute("games", games);
  }
}
