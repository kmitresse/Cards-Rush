/*
 * ForgottenPasswordBeen.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import uppa.project.Global;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;

import static uppa.project.web.servlet.ForgottenPasswordServlet.CreateToken;

public class ForgottenPasswordBean implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String email;

  public ForgottenPasswordBean() {
  }

  /**
   * Validation des paramètres de la requête & gestion de la création du token de réinitialisation de mot de passe
   *
   * @param requestPath le chemin de la requête
   * @return true si l'adresse e-mail est valide, false sinon
   */
  public boolean validate(String requestPath) {
    Game_JPA_DAO_Factory factory = new Game_JPA_DAO_Factory();

    try {
      DAO<User> userDao = factory.getDAOUser();
      User[] user = userDao.findByField("email", email);

      for (User u : user) {
        if (u.getEmail().equals(email)) {
          sendTokenEmail(u, requestPath);
          return true;
        }
      }

    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  /**
   *
   * @param email l'adresse e-mail supposée de l'utilisateur ayant oublié son mot de passe.
   * @return this
   */
  public ForgottenPasswordBean setEmail(String email) {
    this.email = email;
    return this;
  }

  /**
   * Création et envoi d'un token de réinitialisation de mot de passe
   *
   * @param user l'utilisateur ayant oublié son mot de passe
   * @param requestPath le chemin de la requête
   */
  private void sendTokenEmail(User user, String requestPath) {

      String token = UUID.randomUUID().toString();
      RecoveryPasswordToken recoveryPasswordToken = new RecoveryPasswordToken(token, user);
      CreateToken(recoveryPasswordToken);
      sendRecoveryEmail(user.getEmail(), token, requestPath);
  }

  /**
   * Envoi d'un e-mail de réinitialisation de mot de passe
   *
   * @param email l'adresse e-mail du destinataire
   * @param token le token de réinitialisation
   */
  public void sendRecoveryEmail(String email, String token, String requestPath){
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
        "Pour cela, veuillez cliquer sur le lien suivant :"+ requestPath +"/reset-password?token=" + token + "\n\n" +
        "Ce lien est valable 10 minutes.\n" +
        "Si vous n'êtes pas à l'origine de cette demande, ne prenez pas compte de ce mail.\n\n" +
        "Cordialement,\n" +
        "L'équipe CardRush" +
        "\n\n\n\n" +
        "Ce message est généré automatiquement, merci de ne pas y répondre.\n" +
        "CardRush est la propriété de CardRush Corporation, 2024. Tous droits réservés.\n" +
        "CardRush Corporation, 2024, 1 rue de la programmation web, 64000 Pau, France\n" +
        "Société fictive créée dans le cadre d'un projet universitaire. Auteurs du projet: MITRESSÉ Kevin & VABRE Lucàs "
      );
      // Envoi du message
      Transport.send(message);

    } catch (MessagingException e) {
      throw new RuntimeException("Erreur lors de l'envoi de l'e-mail", e);
    }
  }
}
