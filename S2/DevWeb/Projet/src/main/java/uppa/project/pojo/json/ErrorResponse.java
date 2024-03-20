/*
 * ErrorResponse.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.pojo.json;

/**
 * Classe représentant une erreur
 *
 * @author Kevin Mitresse
 * @author Lucàs Vabre
 */
public class ErrorResponse extends HttpResponse{

  /**
   * Type de l'erreur
   */
  private final String error;

  /**
   * Message d'erreur
   */
  private final String message;

  /**
   * Constructeur
   *
   * @param status code HTTP de l'erreur
   * @param error type de l'erreur
   * @param message message d'erreur
   */
  public ErrorResponse(int status, String error, String message) {
    super(status);
    this.error = error;
    this.message = message;
  }

  /**
   * @return le type de l'erreur
   */
  public String getError() {
    return error;
  }

  /**
   * @return le message d'erreur
   */
  public String getMessage() {
    return message;
  }
}
