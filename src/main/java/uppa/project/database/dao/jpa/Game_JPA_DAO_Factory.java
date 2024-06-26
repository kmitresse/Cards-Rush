/*
 * Sports_JDBC_DAOFactory.java, 09/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.dao.jpa;

import uppa.project.database.dao.DAO;
import uppa.project.database.dao.GameDAOFactory;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;
import uppa.project.database.pojo.RecoveryPasswordToken;
import uppa.project.database.pojo.User;
import uppa.project.database.dao.DAOException;

/**
 * Fabrique concrète de DAO pour le schéma relationnel sports avec une implémentation en JDBC.
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 * @see GameDAOFactory
 */
public class Game_JPA_DAO_Factory extends GameDAOFactory {

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

  @Override
  public DAO<RecoveryPasswordToken> getDAORecoveryPasswordToken() throws DAOException {
    if (daoRecoveryPasswordToken == null) daoRecoveryPasswordToken= new DAO_JPA_RecoveryPasswordToken();
    return daoRecoveryPasswordToken;
  }
}
