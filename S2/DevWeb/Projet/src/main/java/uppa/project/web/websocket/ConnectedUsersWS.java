package uppa.project.web.websocket;

import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import uppa.project.database.pojo.User;
import uppa.project.json.websocket.Message;

@ServerEndpoint(value = "/ws/connected-users")
public class ConnectedUsersWS {

  public static final HashMap<Session, User> connections = new HashMap<>();

  @OnOpen
  public void onOpen(Session session) {}

  private void broadcastConnectedUsers() {
    Gson gson = new Gson();
    User[] connectedUsers = connections.values().toArray(new User[0]);

    Message websocketObject = new Message("userList", gson.toJson(connectedUsers));
    String message = gson.toJson(websocketObject);

    for (Session session : connections.keySet()) {
      RemoteEndpoint.Basic remote = session.getBasicRemote();
      try {
        remote.sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @OnClose
  public void onClose(Session session) {
    connections.remove(session);

    // Update connected users list
    broadcastConnectedUsers();
  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    Gson gson = new Gson();
    Message websocketMessage = gson.fromJson(message, Message.class);

    if (websocketMessage.getType().equals("linkUserSession")) {
      User user = gson.fromJson(websocketMessage.getData(), User.class);
      connections.put(session, user);
      broadcastConnectedUsers();
    }
  }
}
