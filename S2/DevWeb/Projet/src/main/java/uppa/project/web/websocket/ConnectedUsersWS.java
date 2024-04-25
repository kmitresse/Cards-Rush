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
  public static final ArrayList<Session> anonymousUsers = new ArrayList<>();

  private static final Gson gson = new Gson();

  private static void broadcast(String message) {
    // Send the message to all anonymous users
    for (Session session : anonymousUsers) {
      try {
        session.getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // Send the message to all connected users
    for (Session session : users.keySet()) {
      try {
        session.getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @OnOpen
  public void onOpen(Session session, @PathParam("user_id") String userId) throws DAOException {
    Message message;
    int id = Integer.parseInt(userId);

    if (id == 0) {
      anonymousUsers.add(session);
    } else {
      // Get the new user
      final DAO<User> userDAO = new Game_JPA_DAO_Factory().getDAOUser();

      User user = userDAO.findById(id);

      // Send the new user to all connected users
      message = new Message("addUser", gson.toJson(new SimpleUser(user)));
      broadcast(message.toJson());

      // Send all connected users to the new user
      users.put(session, user);
    }

    ArrayList<SimpleUser> connectedUsers = new ArrayList<>();
    for (User u : users.values()) connectedUsers.add(new SimpleUser(u));
    message = new Message("init", gson.toJson(connectedUsers));
    session.getAsyncRemote().sendText(message.toJson());
  }

  @OnClose
  public void onClose(Session session, @PathParam("user_id") String userId) {
    int id = Integer.parseInt(userId);

    if (id == 0) {
      anonymousUsers.remove(session);
    } else {
      Message message = new Message("removeUser", gson.toJson(new SimpleUser(users.get(session))));
      users.remove(session);
      broadcast(message.toJson());
    }
  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String rawMessage, Session session) {
    Message message = gson.fromJson(rawMessage, Message.class);

    if (message.getType().equals("invite")) {
      SimpleInvitation invitation = gson.fromJson(message.getData(), SimpleInvitation.class);

      // Find session of the user who receive
      for (Session s : users.keySet()) {
        if (users.get(s).getId().intValue() == invitation.to.id) {
          try {
            s.getBasicRemote().sendText(message.toJson());
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        }
      }
    }
  }

  private static class SimpleInvitation {

    public SimpleUser from;

    public SimpleUser to;

    public int game_id;

    public SimpleInvitation(SimpleUser from, SimpleUser to, int game_id) {
      this.from = from;
      this.to = to;
      this.game_id = game_id;
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
