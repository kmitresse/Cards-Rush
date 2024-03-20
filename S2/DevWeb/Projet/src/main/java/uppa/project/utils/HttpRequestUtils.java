/*
 * RequestUtils.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.utils;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Classe utilitaire pour les requêtes HTTP
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public class HttpRequestUtils {

  /**
   * Récupère le corps de la requête HTTP
   *
   * @param request la requête HTTP
   * @return le corps de la requête
   * @throws IOException en cas de problème
   */
  public static String getRequestBody(HttpServletRequest request) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }
}
