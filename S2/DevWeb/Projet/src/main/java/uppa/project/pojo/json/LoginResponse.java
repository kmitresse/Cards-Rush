/*
 * LoginResponse.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.pojo.json;

import uppa.project.pojo.User;

/**
 * Classe représentant une réponse de connexion
 *
 * @author Kevin Mitresse
 * @author Lucàs Vabre
 */
public class LoginResponse extends HttpResponse {

  /**
   * Utilisateur connecté
   */
  private final User user;

  /**
   * L'url de redirection
   */
  private final String redirect;

  /**
   * Constructeur
   * @param status code HTTP de la réponse
   * @param user utilisateur connecté
   * @param redirect url de redirection
   */
  public LoginResponse(int status, User user, String redirect) {
    super(status);
    this.user = user;
    this.redirect = redirect;
  }

  /**
   * @return l'utilisateur connecté
   */
  public User getUser() {
    return user;
  }

  /**
   * @return l'url de redirection
   */
  public String getRedirect() {
    return redirect;
  }
}
