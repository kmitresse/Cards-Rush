/*
 * HttpResponse.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.json;

/**
 * Classe représentant une réponse HTTP
 *
 * @author Kevin Mitresse
 * @author Lucàs Vabre
 */
public class  HttpResponse {

  /**
   * Défini si la réponse est un succès
   */
  protected final int code;

  /**
   * Message de la réponse
   */
  protected final String message;

  /**
   * Constructeur
   *
   * @param code code HTTP de l'erreur
   * @param message message d'erreur
   */
  public HttpResponse(HttpResponseCode code, String message) {
    this.code = code.getCode();
    this.message = message;
  }
}
