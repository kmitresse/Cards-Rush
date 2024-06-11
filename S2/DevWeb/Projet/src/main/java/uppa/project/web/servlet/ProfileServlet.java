/*
 * LoginServlet.java, 20/03/2024
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
import uppa.project.bean.ProfileBean;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Player;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;
import uppa.project.web.translation.Translator;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

  public void init() {
  }

  /**
   * Affichage de la page de profil
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
    User usersession = (User) request.getSession().getAttribute("user");
    DAO<User> userDAO;
    try {
      userDAO = new Game_JPA_DAO_Factory().getDAOUser();
      User user = userDAO.findById(usersession.getId().intValue());
      request.getSession().setAttribute("user", user);
      request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    } catch (DAOException e) {
      System.out.println(e.getMessage());
      response.sendRedirect(request.getContextPath() + "/lobby");
    }

  }

  /**
   * Gestion de la modification de profil
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    ProfileBean profileBean = new ProfileBean()
      .setUsername(request.getParameter("username"))
      .setOldEmail(request.getParameter("oldEmail"))
      .setEmail(request.getParameter("email"))
      .setOldPassword(request.getParameter("oldPassword"))
      .setPassword(request.getParameter("password"))
      .setGender(request.getParameter("gender"))
      ;

    Gson gson = new Gson();
    HttpResponse httpResponse;
    if (profileBean.validate()) {
      request.getSession().setAttribute("user", profileBean.getUser());
      httpResponse = new HttpResponse(
        HttpResponseCode.OK,
        "Register success"
      );
    } else {
      httpResponse = profileBean.getError();
    }
    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  public void destroy() {
  }
}
