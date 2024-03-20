/*
 * ErrorApi.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.servlet.json;

import com.google.gson.Gson;

/**
 * Classe représentant une erreur pour l'API
 *
 * @author Kevin Mitresse
 * @author Lucàs Vabre
 */
public class ErrorApi {

  /**
   * Code HTTP de l'erreur
   */
  private final int status;

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
  public ErrorApi(int status, String error, String message) {
    this.status = status;
    this.error = error;
    this.message = message;
  }

  /**
   * @return le code HTTP de l'erreur
   */
  public int getStatus() {
    return status;
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

  /**
   * @return la représentation JSON de l'erreur
   */
  public String toJson() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
