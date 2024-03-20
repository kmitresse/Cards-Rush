/*
 * ErrorResponse.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.pojo.json;

/**
 * Classe représentant une réponse HTTP
 *
 * @author Kevin Mitresse
 * @author Lucàs Vabre
 */
public abstract class  HttpResponse {

  /**
   * Code HTTP de la réponse
   */
  protected final int status;

  /**
   * Constructeur
   *
   * @param status code HTTP de l'erreur
   */
  public HttpResponse(int status) {
    this.status = status;
  }

  /**
   * @return le code HTTP de la réponse
   */
  public int getStatus() {
    return status;
  }
}
