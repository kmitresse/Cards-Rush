package uppa.project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import uppa.project.dao.DAOException;
import uppa.project.dao.jpa.DAO_JPA_RecoveryPasswordToken;
import uppa.project.dao.jpa.DAO_JPA_User;
import uppa.project.pojo.RecoveryPasswordToken;
import uppa.project.pojo.User;

@WebServlet(name = "resetPasswordServlet", value = "/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    RecoveryPasswordToken recoveryPasswordToken = findRecoveryToken(request.getParameter("token"));
    if (recoveryPasswordToken == null) {
      response.sendRedirect(request.getContextPath() + "/error?code=404");
      return;
    }
    request.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(request, response);
    }

    /**
     * Gestion de la réinitialisation de mot de passe
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      RecoveryPasswordToken recoveryPasswordToken = findRecoveryToken(request.getParameter("token"));
      User user = recoveryPasswordToken.getUser();
      String newPassword = request.getParameter("newPassword");
      String confirmPassword = request.getParameter("confirmPassword");
      System.out.println("newPassword: " + newPassword);
      System.out.println("confirmPassword: " + confirmPassword);
      System.out.println(!newPassword.equals(confirmPassword));
      if (!newPassword.equals(confirmPassword)) {
        System.out.println("ici");
        response.sendRedirect(request.getContextPath() + "/reset-password?error=1&token=" + recoveryPasswordToken.getToken());
        return;
      }
      user.setPassword(newPassword);
      DAO_JPA_User daoJpaUser = null;
      try {
        daoJpaUser = new DAO_JPA_User();
        daoJpaUser.update(user);
        response.sendRedirect(request.getContextPath() + "/login?success=password-modified");
      } catch (DAOException e) {
        response.sendRedirect(request.getContextPath() + "/reset-password?error=2");
      }
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
