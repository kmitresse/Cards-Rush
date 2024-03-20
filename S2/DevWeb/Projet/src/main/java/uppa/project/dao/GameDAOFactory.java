/*
 * GameDAOFactory.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao;

import uppa.project.pojo.Game;
import uppa.project.pojo.Player;
import uppa.project.pojo.User;

/**
 * Fabrique abstraite de DAO pour le schéma du jeu
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public abstract class GameDAOFactory {

  /**
   * @return le DAO pour la classe/table User
   * @throws DAOException en cas de problème
   * @see User
   */
  public abstract DAO<User> getDAOUser() throws DAOException;

  /**
   * @return le DAO pour la classe/table Game
   * @throws DAOException en cas de problème
   * @see Game
   */
  public abstract DAO<Game> getDAOGame() throws DAOException;

  /**
   * @return le DAO pour la classe/table Player
   * @throws DAOException en cas de problème
   * @see Player
   */
  public abstract DAO<Player> getDAOPlayer() throws DAOException;

}
