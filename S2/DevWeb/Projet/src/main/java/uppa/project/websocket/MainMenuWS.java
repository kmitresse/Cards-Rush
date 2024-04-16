package uppa.project.websocket;

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
import uppa.project.pojo.User;
import uppa.project.pojo.json.websocket.Message;

@ServerEndpoint(value = "/main-menu")
public class MainMenuWS {

  private static final HashMap<Session, User> connections = new HashMap<>();

  @OnOpen
  public void onOpen(Session session) {
    System.out.println("Connexion WebSocket ouverte : " + session.getId());
  }

  private void broadcastConnectedUsers() {
    Gson gson = new Gson();
    User[] connectedUsers = connections.values().toArray(new User[0]);

    Message websocketObject = new Message("userList", gson.toJson(connectedUsers));
    String message = gson.toJson(websocketObject);

    System.out.println("Broadcasting connected users : " + message);

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
    System.out.println("Connexion WebSocket fermée : " + session.getId());
    connections.remove(session);

    // Update connected users list
    broadcastConnectedUsers();
  }

  @OnError
  public void onError(Throwable throwable) {
    System.out.println("Erreur WebSocket : " + throwable.getMessage());
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    Gson gson = new Gson();
    Message websocketMessage = gson.fromJson(message, Message.class);

    System.out.println("Message reçu : " + websocketMessage);

    if (websocketMessage.getType().equals("linkUserSession")) {
      User user = gson.fromJson(websocketMessage.getData(), User.class);
      System.out.println(user);

      connections.put(session, user);
      broadcastConnectedUsers();
    }
  }
}
