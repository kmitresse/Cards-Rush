/*
 * Sports_JDBC_DAOFactory.java, 09/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao.jpa;

import uppa.project.dao.DAO;
import uppa.project.pojo.*;
import uppa.project.dao.DAOException;
import uppa.project.dao.GameDAOFactory;

/**
 * Fabrique concrète de DAO pour le schéma relationnel sports avec une implémentation en JDBC.
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 * @see GameDAOFactory
 */
public class Game_JPA_DAO_Factory extends GameDAOFactory {

  /**
   * DAO pour les utilisateurs
   */
  private DAO_JPA_User daoUser = null;

  /**
   * DAO pour les parties
   */
  private DAO_JPA_Game daoGame = null;

  /**
   * DAO pour les joueurs
   */
  private DAO_JPA_Player daoPlayer = null;

  @Override
  public DAO<User> getDAOUser() throws DAOException {
    if (daoUser == null) daoUser = new DAO_JPA_User();
    return daoUser;
  }

  @Override
  public DAO<Game> getDAOGame() throws DAOException {
    if (daoGame == null) daoGame = new DAO_JPA_Game();
    return daoGame;
  }
  @Override
  public DAO<Player> getDAOPlayer() throws DAOException {
    if (daoPlayer == null) daoPlayer = new DAO_JPA_Player();
    return daoPlayer;
  }
}
