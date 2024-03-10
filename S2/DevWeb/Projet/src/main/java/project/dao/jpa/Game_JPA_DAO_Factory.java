/*
 * Sports_JDBC_DAOFactory.java, 09/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package project.dao.jpa;

import project.dao.DAO;
import project.pojo.*;
import project.dao.DAOException;
import project.dao.GameDAOFactory;

/**
 * Fabrique concrète de DAO pour le schéma relationnel sports avec une implémentation en JDBC.
 */
public class Game_JPA_DAO_Factory extends GameDAOFactory {

  private DAO_JPA_User daoUser = null;

  private DAO_JPA_Game daoGame = null;

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
