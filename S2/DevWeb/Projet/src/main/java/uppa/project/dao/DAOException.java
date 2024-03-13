/*
 * DAOException.java, 09/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao;

/**
 * Exception spécifique aux problèmes d'accès aux données via un DAO
 */
public class DAOException extends Exception {

  public DAOException() {
    super();
  }

  public DAOException(String message) {
    super(message);
  }
}
