package uppa.project.web.websocket;

import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.User;
import uppa.project.json.websocket.Message;

@ServerEndpoint(value = "/ws/users/{user_id}")
public class ConnectedUsersWS {

  public static final HashMap<Session, User> users = new HashMap<>();
  private static final Gson gson = new Gson();

  @OnOpen
  public void onOpen(Session session, @PathParam("user_id") String userId) throws DAOException {
    Message message;
    final DAO<User> userDAO = new Game_JPA_DAO_Factory().getDAOUser();

    int id = Integer.parseInt(userId);
    User user = userDAO.findById(id);

    // Send the new user to all connected users
    message = new Message("addUser", gson.toJson(new SimpleUser(user)));
    broadcast(message.toJson());

    // Send all connected users to the new user
    users.put(session, user);
    ArrayList<SimpleUser> connectedUsers = new ArrayList<>();
    for (User u : users.values()) connectedUsers.add(new SimpleUser(u));
    message = new Message("init", gson.toJson(connectedUsers));
    session.getAsyncRemote().sendText(message.toJson());
  }

  @OnClose
  public void onClose(Session session) {
    users.remove(session);

    Message message = new Message("removeUser", gson.toJson(new SimpleUser(users.get(session))));
    broadcast(message.toJson());
  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    // Do nothing
  }

  private static void broadcast(String message) {
    for (Session session : users.keySet()) {
      try {
        session.getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static class SimpleUser {
    public int id;
    public String username;

    public SimpleUser(User user) {
      this.id = user.getId().intValue();
      this.username = user.getUsername();
    }
  }
}
