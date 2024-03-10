/*
 * AbstractDAOFactory.java, 09/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package project.dao;

import project.dao.jpa.Game_JPA_DAO_Factory;

/**
 * Factory renvoyant une factory de DAO en fonction du support de persistance choisi
 */
public class AbstractDAOFactory {

  public static GameDAOFactory getDAOFactory(PersistenceKind type) {
    if (type.equals(PersistenceKind.JPA)) return new Game_JPA_DAO_Factory();
    return null;
  }
}
