/*
 * AbstractDAOFactory.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao;

import uppa.project.dao.jpa.Game_JPA_DAO_Factory;

/**
 * Factory renvoyant une factory de DAO en fonction du support de persistance choisi
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public class AbstractDAOFactory {

  /**
   * Renvoie une factory de DAO en fonction du support de persistance choisi
   *
   * @param type le support de persistance
   * @return la factory de DAO
   * @see PersistenceKind
   */
  public static GameDAOFactory getDAOFactory(PersistenceKind type) {
    if (type.equals(PersistenceKind.JPA)) return new Game_JPA_DAO_Factory();
    return null;
  }
}
