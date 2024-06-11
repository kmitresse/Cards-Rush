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
import uppa.project.web.translation.Translator;

@WebServlet(name = "forgottenPasswordServlet", value = "/forgotten-password")
public class ForgottenPasswordServlet extends HttpServlet {
  final static EntityManager em = EntityManagerProvider.getInstance();
  public void init() {
  }

  /**
   * Affichage de la page de réinitialisation de mot de passe
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
    request.setAttribute("current", "forgotten-password");
    request.getRequestDispatcher("/WEB-INF/pages/forgotten-password.jsp").forward(request, response);
  }

  /**
   * Gestion de la réinitialisation de mot de passe
   *
   * @param request la requête
   * @param response la réponse
   * @throws IOException si une erreur d'entrée/sortie survient
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();

    ForgottenPasswordBean forgottenPasswordBean = new ForgottenPasswordBean()
      .setEmail(request.getParameter("email"))
      .setTranslator((Translator) request.getSession().getAttribute("translator"));

    Gson gson = new Gson();
    HttpResponse httpResponse;
    String url = request.getRequestURL().toString();
    String uri = url.substring(0, url.lastIndexOf("/"));
    if (forgottenPasswordBean.validate(uri)) {
      httpResponse = new HttpResponse(HttpResponseCode.OK, "Mail sent");
    } else {
      httpResponse = new HttpResponse(HttpResponseCode.NOT_FOUND, "L'email renseigné est inconnu de nos services.");
    }

    out.println(gson.toJson(httpResponse));
    out.flush();
  }

  /**
   * Création d'un token de réinitialisation de mot de passe
   *
   * @param token le token
   */
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
