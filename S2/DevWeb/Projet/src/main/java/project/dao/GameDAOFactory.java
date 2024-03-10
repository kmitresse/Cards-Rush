/*
 * SportsDAOFactory.java, 09/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package project.dao;

import project.pojo.*;

/**
 * Fabrique abstraite de DAO pour le schéma sports
 */
public abstract class GameDAOFactory {

  /**
   * @return le DAO pour la classe/table User
   * @throws DAOException en cas de problème
   */
  public abstract DAO<User> getDAOUser() throws DAOException;

  /**
   * @return le DAO pour la classe/table Game
   * @throws DAOException en cas de problème
   */
  public abstract DAO<Game> getDAOGame() throws DAOException;

  /**
   * @return le DAO pour la classe/table Player
   * @throws DAOException en cas de problème
   */
  public abstract DAO<Player> getDAOPlayer() throws DAOException;

}
