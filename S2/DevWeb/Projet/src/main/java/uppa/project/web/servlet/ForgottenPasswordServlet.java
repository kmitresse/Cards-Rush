/*
 * ForgottenPasswordServlet.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.web.servlet;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import javax.mail.Message;
import uppa.project.Global;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_User;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import uppa.project.database.dao.EntityManagerProvider;


@WebServlet(name = "forgottenPasswordServlet", value = "/forgotten-password")
public class ForgottenPasswordServlet extends HttpServlet {
  final static EntityManager em = EntityManagerProvider.getInstance();
  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setAttribute("current", "forgotten-password");
    request.getRequestDispatcher("/WEB-INF/views/forgotten-password.jsp").forward(request, response);
  }

  /**
   * Gestion de la réinitialisation de mot de passe
   *
   * @param request
   * @param response
   * @throws IOException
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String email = request.getParameter("email");
    User user = getUserByEmail(email);
    if (user == null) {
      response.sendRedirect(request.getContextPath() + "/forgotten-password?error=1");
    } else {
      String token = UUID.randomUUID().toString();
      RecoveryPasswordToken recoveryPasswordToken = new RecoveryPasswordToken(token, user);
      CreateToken(recoveryPasswordToken);
      sendRecoveryEmail(email, token, request);
      response.sendRedirect(request.getContextPath() + "/forgotten-password?success=200");
    }
  }

  /**
   * Envoi d'un e-mail de réinitialisation de mot de passe
   *
   * @param email
   * @param token
   */
  public void sendRecoveryEmail(String email, String token, HttpServletRequest request){
      String host = Global.MAIL_HOST;
      String port = Global.MAIL_PORT;
      String username = Global.MAIL_USERNAME;
      String password = Global.MAIL_PASSWORD;

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", port);

      Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      });

      try {
        // Création du message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Réinitialisation de votre mot de passe");
        message.setText("Bonjour,\n\n" +
          "Vous avez demandé la réinitialisation de votre mot de passe.\n" +
          "Pour cela, veuillez cliquer sur le lien suivant :"+ request.getContextPath() +"/project_war_exploded/reset-password?token=" + token + "\n\n" +
          "Cordialement,\n" +
          "L'équipe CardRush");
        // Envoi du message
        Transport.send(message);

        System.out.println("E-mail envoyé avec succès à : " + email);
      } catch (MessagingException e) {
        throw new RuntimeException("Erreur lors de l'envoi de l'e-mail", e);
      }
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
