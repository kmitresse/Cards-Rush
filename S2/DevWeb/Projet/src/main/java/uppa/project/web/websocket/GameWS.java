package uppa.project.web.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import uppa.project.database.dao.DAOException;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.User;

@ServerEndpoint(value = "/ws/game/{game_id}/{user_id}")
public class GameWS {

  public static final HashMap<Session, User> users = new HashMap<>();
  public static final HashMap<Game, ArrayList<User>> games = new HashMap<>();

  @OnOpen
  public void onOpen(Session session, @PathParam("game_id") String gameId, @PathParam("user_id") String userId) throws DAOException {

  }

  @OnClose
  public void onClose(Session session, @PathParam("game_id") String gameId, @PathParam("user_id") String userId) {

  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    // Do nothing
  }
}
