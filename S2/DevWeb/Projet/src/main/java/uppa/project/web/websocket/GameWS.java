package uppa.project.web.websocket;

import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.DAO_JPA_User;
import uppa.project.database.pojo.Card;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;
import uppa.project.database.pojo.User;
import uppa.project.json.websocket.ClickChoice;
import uppa.project.json.websocket.Message;
import uppa.project.json.websocket.SimpleGame;
import uppa.project.json.websocket.SimplePlayer;
import uppa.project.json.websocket.SimpleUser;
import uppa.project.utils.GameProvider;

@ServerEndpoint(value = "/ws/game/{game_id}")
public class GameWS {

  Gson gson = new Gson();

  private static final HashMap<Game, ArrayList<Player>> games = new HashMap<>();

  private Game game;
  private Player player;

  @OnOpen
  public void onOpen(Session session, @PathParam("game_id") String gameId) throws DAOException {
    this.game = GameProvider.getGame(Integer.parseInt(gameId));
    if (!games.containsKey(game)) games.put(game, new ArrayList<>());
  }

  @OnClose
  public void onClose(Session session, @PathParam("game_id") String gameId) throws DAOException {
    games.get(game).remove(this.player);

    // Broadcast the new player
    ArrayList<SimplePlayer> simplePlayerList = new ArrayList<>();
    for (Player player : games.get(game)) simplePlayerList.add(new SimplePlayer(player));

    broadcast(new Message("updatePlayerList", gson.toJson(simplePlayerList)).toJson());
  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String rawMessage, Session session, @PathParam("game_id") String gameId) throws DAOException {
    DAO<User> userDAO = new DAO_JPA_User();

    Message message = gson.fromJson(rawMessage, Message.class);

    if (message.getType().equals("connection")) {
      SimpleUser simpleUser = gson.fromJson(message.getData(), SimpleUser.class);

      // find the user
      User user = userDAO.findById(simpleUser.getId());

      this.player = new Player(game, user, session);
      games.get(game).add(this.player);

      ArrayList<SimplePlayer> simplePlayerList = new ArrayList<>();
      for (Player p : games.get(game)) simplePlayerList.add(new SimplePlayer(p));

      // Broadcast the new player
      broadcast(new Message("updatePlayerList", gson.toJson(simplePlayerList)).toJson());
    }
    if (message.getType().equals("start")) {
      game.setGameState(Game.GameState.STARTED);
      broadcast(new Message("start", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());
      // TODO Start Timer
    }
    if (message.getType().equals("click")) {
      ClickChoice choice = gson.fromJson(message.getData(), ClickChoice.class);

      int playerScore = player.getScore();
      Card gameCard = game.getDeck().getCards().get(game.getCurrentRound());
      Card playerCard = player.getDeck().getCards().get(game.getCurrentRound());

      player.setCurrentClick(choice);

      // get the number of players who clicked
      int gameClickCount = 0;
      for (Player player : games.get(game)) {
        if (player.getCurrentClick() != null) gameClickCount++;
      }

      // Click count
      player.incrementClickCount();

      // Right click count
      if (gameClickCount == 1) player.incrementRapidClickCount();

      // Check if the player has clicked on the right card
      switch (choice) {
        case COLOR_VALUE -> {
          if (gameCard.getColor().equals(playerCard.getColor()) && gameCard.getValue() == playerCard.getValue()) {
            player.incrementRightClickCount();
            player.setScore(playerScore + 2);
          } else {
            player.setScore(playerScore - 1);
          }
        }
        case COLOR -> {
          if (gameCard.getColor().equals(playerCard.getColor())) {
            if (gameCard.getValue() != playerCard.getValue()) {
              player.incrementRightClickCount();
              player.setScore(playerScore + 2);
            } else {
              player.setScore(playerScore + 1);
            }
          } else {
            player.setScore(playerScore - 1);
          }
        }
        case VALUE -> {
          if(gameCard.getValue() == playerCard.getValue()) {
            if (!gameCard.getColor().equals(playerCard.getColor())) {
              player.incrementRightClickCount();
              player.setScore(playerScore + 2);
            } else {
              player.setScore(playerScore + 1);
            }
          } else {
            player.setScore(playerScore - 1);
          }
        }
        case NONE -> {
          if (!gameCard.getColor().equals(playerCard.getColor()) && gameCard.getValue() != playerCard.getValue()) {
            player.incrementRightClickCount();
            player.setScore(playerScore + 2);
          } else {
            player.setScore(playerScore - 1);
          }
        }
      }

      // Broadcast the player choice with score

      broadcast(new Message("updatePlayer", gson.toJson(new SimplePlayer(player))).toJson());

      System.out.println(gameClickCount + " / " + games.get(game).size());

      // If all players have clicked
      if (gameClickCount >= games.get(game).size()) {

        // Reset the current click for all players
        for (Player p : games.get(game)) p.setCurrentClick(null);

        // Check if the game is over
        if (game.nextRound()) { // TODO: if score is the same add a round
          broadcast(new Message("nextRound", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());
          // TODO Start Timer
        } else {
          // TODO: determine the winner

          // Broadcast the end of the game
          broadcast(new Message("end", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());

          // TODO: persist the game in the database
//          EntityManager em = EntityManagerProvider.getInstance();
//          game.setPlayers(games.get(game));
//
//          em.getTransaction().begin();
//          em.persist(game);
//          em.getTransaction().commit();
        }
      }
    }
  }

  private void broadcast(String message) {
    for (Player player : games.get(game)) {
      try {
        player.getSession().getBasicRemote().sendText(message);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
