package uppa.project.web.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import uppa.project.bean.RegisterBean;
import uppa.project.bean.ResetPasswordBean;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_RecoveryPasswordToken;
import uppa.project.database.dao.jpa.DAO_JPA_User;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

@WebServlet(name = "resetPasswordServlet", value = "/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      RecoveryPasswordToken token = findRecoveryToken(request.getParameter("token"));
      if (token == null) {
        request.getRequestDispatcher("/WEB-INF/pages/invalid-token-password.jsp").forward(request, response);
        return;
      }
      if (token.getExpirationDate().compareTo(new java.util.Date()) < 0){
        request.getRequestDispatcher("/WEB-INF/pages/expired-token-password.jsp").forward(request, response);
        return;
      }
      request.setAttribute("current", "reset-password");
      request.getRequestDispatcher("/WEB-INF/pages/reset-password.jsp").forward(request, response);
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

      ResetPasswordBean resetPasswordBean = new ResetPasswordBean()
        .setToken(request.getParameter("token"))
        .setPassword(request.getParameter("password"))
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
