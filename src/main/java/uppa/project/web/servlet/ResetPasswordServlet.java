package uppa.project.web.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.bean.ResetPasswordBean;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_RecoveryPasswordToken;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;
import uppa.project.web.translation.Translator;

@WebServlet(name = "resetPasswordServlet", value = "/reset-password")
public class ResetPasswordServlet extends HttpServlet {
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
      RecoveryPasswordToken token = findRecoveryToken(request.getParameter("token"));
      if (token == null) {
        request.getRequestDispatcher("/WEB-INF/pages/errors/invalid-token-password.jsp").forward(request, response);
        return;
      }
      if (token.getExpirationDate().compareTo(new java.util.Date()) < 0){
        request.getRequestDispatcher("/WEB-INF/pages/errors/expired-token-password.jsp").forward(request, response);
        return;
      }
      request.setAttribute("current", "reset-password");
      request.getRequestDispatcher("/WEB-INF/pages/reset-password.jsp").forward(request, response);
    }

    /**
     * Gestion de la réinitialisation de mot de passe
     *
     * @param request la requête
     * @param response la réponse
     * @throws IOException si une erreur d'entrée/sortie survient
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Translator translator = (Translator) request.getSession().getAttribute("translator");
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();

      ResetPasswordBean resetPasswordBean = new ResetPasswordBean()
        .setTranslator(translator)
        .setToken(request.getParameter("token"))
        .setPassword(request.getParameter("password"))
        .setConfirmPassword(request.getParameter("repassword"))
        ;

      Gson gson = new Gson();
      HttpResponse httpResponse;
      if (resetPasswordBean.validate()) {
        httpResponse = new HttpResponse(
          HttpResponseCode.OK,
          "Register success"
        );
      } else {
        httpResponse = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR, resetPasswordBean.getErrorMessage());
      }

      out.println(gson.toJson(httpResponse));
      out.flush();
    }

    /**
     * Recherche d'un token de réinitialisation de mot de passe
     *
     * @param token le token
     * @return le token de réinitialisation de mot de passe
     */
    public static RecoveryPasswordToken findRecoveryToken(String token) {
      try {
        DAO_JPA_RecoveryPasswordToken daoJpaRecoveryPasswordToken = new DAO_JPA_RecoveryPasswordToken();
        RecoveryPasswordToken[] recoveryPasswordTokens = daoJpaRecoveryPasswordToken.findByField("token",token);
        if (recoveryPasswordTokens.length == 0) {
          return null;
        }
        return recoveryPasswordTokens[0];
      } catch (DAOException e) {
        throw new RuntimeException(e);
      }
    }
}
