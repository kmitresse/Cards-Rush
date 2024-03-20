/*
 * DAOException.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao;

/**
 * Exception spécifique aux problèmes d'accès aux données via un DAO
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 * @see DAO
 */
public class DAOException extends Exception {

  /**
   * Constructeur par défaut
   */
  public DAOException() {
    super();
  }

  /**
   * Constructeur avec message
   *
   * @param message le message de l'exception
   */
  public DAOException(String message) {
    super(message);
  }
}
