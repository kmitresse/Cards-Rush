/*
 * ForgottenPasswordServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.bean.ForgottenPasswordBean;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_User;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

@WebServlet(name = "forgottenPasswordServlet", value = "/forgotten-password")
public class ForgottenPasswordServlet extends HttpServlet {
  final static EntityManager em = EntityManagerProvider.getInstance();
  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setAttribute("current", "forgotten-password");
    request.getRequestDispatcher("/WEB-INF/pages/forgotten-password.jsp").forward(request, response);
  }

  /**
   * Gestion de la réinitialisation de mot de passe
   *
   * @param request
   * @param response
   * @throws IOException
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    ForgottenPasswordBean forgottenPasswordBean = new ForgottenPasswordBean()
      .setEmail(request.getParameter("email"));

    Gson gson = new Gson();
    HttpResponse httpResponse;
    String url = request.getRequestURL().toString();
    //retirer le dernier segment de l'url pour avoir le contextPath
    String uri = url.substring(0, url.lastIndexOf("/"));
    System.out.println(uri);
    if (forgottenPasswordBean.validate(uri)) {
      httpResponse = new HttpResponse(HttpResponseCode.OK, "Mail sent");
    } else {
      httpResponse = new HttpResponse(HttpResponseCode.NOT_FOUND, "<strong>Erreur:</strong> L'email renseigné est inconnu de nos services.");
    }

    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  public static User getUserByEmail(String email){
      try {
        DAO_JPA_User daoJpaUser = new DAO_JPA_User();
        User[] users = daoJpaUser.findByField("email", email);
        if (users.length == 0) {
          return null;
        }
        return users[0];
      } catch (DAOException e) {
        throw new RuntimeException(e);
      }
  }
  public static void CreateToken(RecoveryPasswordToken token){
    Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
    em.getTransaction().begin();
    try {
      DAO<RecoveryPasswordToken> daoJpaRecoveryPasswordToken = jpaDaoFactory.getDAORecoveryPasswordToken();
      daoJpaRecoveryPasswordToken.create(token);
      em.getTransaction().commit();
    } catch (DAOException e) {
      em.getTransaction().rollback();
      throw new RuntimeException(e);
    }
  }

  public void destroy() {
  }
}
