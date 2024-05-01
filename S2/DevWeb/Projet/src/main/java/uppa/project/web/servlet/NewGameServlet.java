/*
 * MainMenuServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.bean.NewGameBean;
import uppa.project.database.pojo.Game;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

@WebServlet(name = "newGameServlet", value = "/new")
public class NewGameServlet extends HttpServlet {

  public void init() {
  }

  /**
   * Affichage de la page de création de partie
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   * @throws ServletException si une erreur de servlet survient
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/pages/new-game.jsp").forward(request, response);
  }

  /**
   * Création d'une nouvelle partie
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Gson gson = new Gson();

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    NewGameBean newGameBean = new NewGameBean()
      .setDifficulty(request.getParameter("difficulty"))
      .setNbRounds(request.getParameter("nbRounds"))
      .setTimer(request.getParameter("timer"))
      .setNbValues(request.getParameter("nbValues"))
      .setNbColors(request.getParameter("nbColors"))
    ;

    HttpResponse httpResponse;
    if (newGameBean.validate()) {
      Game game = newGameBean.getGame();
      httpResponse = new HttpResponse(HttpResponseCode.OK, game.getId().toString());
    } else {
      httpResponse = newGameBean.getError();
    }

    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  public void destroy() {
  }
}
