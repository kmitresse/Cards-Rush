package uppa.project.utils;

import java.util.HashMap;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.DAO_JPA_Game;
import uppa.project.database.pojo.Game;

public class GameProvider {

  private static DAO<Game> gameDAO;

  static {
    try {
      gameDAO = new DAO_JPA_Game();
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  private static HashMap<Integer, Game> games = new HashMap<>();

  public static Game getGame(int gameId) throws DAOException {
    if (!games.containsKey(gameId)) {
      Game game = gameDAO.findById(gameId);
      games.put(gameId, game);
    }

    if (games.get(gameId) == null) {
      throw new DAOException("Game not found");
    }
    return games.get(gameId);
  }
}
