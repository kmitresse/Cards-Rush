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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import uppa.project.bean.PlayerBean;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.DAO_JPA_User;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Card;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;
import uppa.project.database.pojo.User;
import uppa.project.json.websocket.ClickChoice;
import uppa.project.json.websocket.Message;
import uppa.project.json.websocket.SimpleGame;
import uppa.project.json.websocket.SimplePlayer;
import uppa.project.json.websocket.SimpleUser;

@ServerEndpoint(value = "/ws/game/{game_id}")
public class GameWS {

  private static final HashMap<Game, ArrayList<Player>> games = new HashMap<>();
  private static final HashMap<Game, Timer> timers = new HashMap<>();
  Gson gson = new Gson();
  private Game game;
  private Player player;

  @OnOpen
  public void onOpen(Session session, @PathParam("game_id") String gameId) throws DAOException {
    DAO<Game> gameDAO = new Game_JPA_DAO_Factory().getDAOGame();
    this.game = gameDAO.findById(Integer.parseInt(gameId));
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

      // Diffusion de la nouvelle liste de joueurs
      broadcast(new Message("updatePlayerList", gson.toJson(simplePlayerList)).toJson());
    }

    if (message.getType().equals("start")) {
      game.setGameState(Game.GameState.STARTED);
      broadcast(new Message("start", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());

      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          // Fin du timer
          broadcast(new Message("timerEnd", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());
          timer.cancel();
        }
      }, game.getTimer() * 1000L);
      timers.put(game, timer);
    }

    if (message.getType().equals("click")) {
      ClickChoice choice = gson.fromJson(message.getData(), ClickChoice.class);

      int playerScore = player.getScore();
      Card gameCard = game.getDeck().getCards().get(game.getCurrentRound());
      Card playerCard = player.getDeck().getCards().get(game.getCurrentRound());

      player.setCurrentClick(choice);

      // Récupérer le nombre de joueurs ayant cliqué
      int gameClickCount = 0;
      for (Player player : games.get(game)) {
        if (player.getCurrentClick() != null) gameClickCount++;
      }

      // Compteur de clics
      player.incrementClickCount();

      // Compteur de clics rapides
      if (gameClickCount == 1 && choice != ClickChoice.TIMER_END) {
        player.incrementRapidClickCount();
        player.addToScore(1);
      };

      // Vérifier le choix du joueur et attribuer les points
      if (game.getDifficulty().equals(Game.Difficulty.EASY)) {
        switch (choice) {
          case COLOR_VALUE -> {
            if (gameCard.equals(playerCard)) {
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
                player.incrementPartialRightClickCount();
                player.setScore(playerScore + 1);
              }
            } else {
              player.setScore(playerScore - 1);
            }
          }
          case VALUE -> {
            if (gameCard.getValue() == playerCard.getValue()) {
              if (!gameCard.getColor().equals(playerCard.getColor())) {
                player.incrementRightClickCount();
                player.setScore(playerScore + 2);
              } else {
                player.incrementPartialRightClickCount();
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
          case TIMER_END -> {}
        }
      } else {
        int nbSameCard = countSameCard(gameCard, games.get(game), game.getCurrentRound());
        int nbSameColor = countSameColor(gameCard, games.get(game), game.getCurrentRound());
        int nbSameValue = countSameValue(gameCard, games.get(game), game.getCurrentRound());
        int nbNone = countNone(gameCard, games.get(game), game.getCurrentRound());
        switch (choice) {
          case COLOR_VALUE -> {
            if ((nbSameCard >= nbSameColor) && (nbSameCard >= nbSameValue) && (nbSameCard >= nbNone)) {
              player.incrementRightClickCount();
              player.setScore(playerScore + 2);
            } else {

              player.setScore(playerScore - 1);
            }
          }
          case COLOR -> {
            if ((nbSameCard >= nbSameColor) && (nbSameCard >= nbSameValue) && (nbSameCard >= nbNone)) {
              player.incrementPartialRightClickCount();
              player.setScore(playerScore + 1);
            } else if ((nbSameColor > nbSameCard) && (nbSameColor >= nbSameValue) && (nbSameColor >= nbNone)) {
              player.incrementRightClickCount();
              player.setScore(playerScore + 2);
            } else {
              player.setScore(playerScore - 1);
            }
          }
          case VALUE -> {
            if ((nbSameCard >= nbSameColor) && (nbSameCard >= nbSameValue) && (nbSameCard >= nbNone)) {
              player.incrementPartialRightClickCount();
              player.setScore(playerScore + 1);
            } else if ((nbSameValue > nbSameCard) && (nbSameValue > nbSameColor) && (nbSameValue >= nbNone)) {
              player.incrementRightClickCount();
              player.setScore(playerScore + 2);
            } else {
              player.setScore(playerScore - 1);
            }
          }
          case NONE -> {
            if ((nbNone > nbSameCard) && (nbNone >= nbSameColor) && (nbNone > nbSameValue)) {
              player.incrementRightClickCount();
              player.setScore(playerScore + 2);
            } else {
              player.setScore(playerScore - 1);
            }
          }
          case TIMER_END -> {}
        }
      }

      // Diffuser le score du joueur
      broadcast(new Message("updatePlayer", gson.toJson(new SimplePlayer(player))).toJson());

      // Si tous les joueurs ont cliqué
      if (gameClickCount >= games.get(game).size()) {
        // Stopper le timer
        timers.get(game).cancel();

        // Réinitialiser les clics courrants
        for (Player p : games.get(game)) p.setCurrentClick(null);

        List<Player> players = games.get(game);
        players.sort((p1, p2) -> {
          if (p1.getScore() == p2.getScore()) {
            return p2.getRapidClickCount() - p1.getRapidClickCount();
          }
          return p2.getScore() - p1.getScore();
        });

        Player theoricWinner = players.get(0);
        Player second = players.get(1);

        // Vérifier si la partie est terminée
        if (game.nextRound() || (second.getScore() == theoricWinner.getScore() && second.getRapidClickCount() == theoricWinner.getRapidClickCount())) {
          broadcast(new Message("nextRound", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());
          Timer timer = new Timer();
          timer.schedule(new TimerTask() {
            @Override
            public void run() {
              // Diffuser la fin du timer
              broadcast(new Message("timerEnd", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());
              timer.cancel();
            }
          }, game.getTimer() * 1000L);
          timers.put(game, timer);
        } else {
          theoricWinner.setWinner();

          EntityManager em = EntityManagerProvider.getFactory().createEntityManager();
          em.getTransaction().begin();
          for (Player p : games.get(game)) {
            PlayerBean playerBean = new PlayerBean(p);
            if (playerBean.validate()) System.out.println("Player " + p.getUser().getUsername() + " sauvegardé en base de données");
          }
          em.getTransaction().commit();

          // Diffusion de la fin de la partie
          broadcast(new Message("end", gson.toJson(new SimpleGame(game, games.get(game)))).toJson());
        }
      }
    }
  }

  /**
   * Retourne le nombre de joueurs avec une carte identique à celle du plateau
   *
   * @param gameCard     carte du plateau
   * @param players      liste des joueurs
   * @param currentRound manche courante
   * @return nombre de cartes identiques à celle du plateau
   */
  private int countSameCard(Card gameCard, List<Player> players, int currentRound) {
    int counter = 0;
    for (Player player : players) {
      Card card = player.getDeck().getCards().get(currentRound);
      if (gameCard.equals(card)) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Retourne le nombre de joueurs avec une carte avec seulement la couleur correspondante à celle du plateau
   *
   * @param gameCard
   * @param players
   * @param currentRound
   * @return nombre de couleurs identiques à celle du plateau
   */
  private int countSameColor(Card gameCard, List<Player> players, int currentRound) {
    int counter = 0;
    for (Player player : players) {
      Card card = player.getDeck().getCards().get(currentRound);
      if (gameCard.getColor().equals(card.getColor()) && !gameCard.getValue().equals(card.getValue())) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Retourne le nombre de joueurs avec une carte avec seulement la valeur correspondante à celle du plateau
   *
   * @param gameCard     carte du plateau
   * @param players      liste des joueurs
   * @param currentRound manche courante
   * @return nombre de valeurs identiques à celle du plateau
   */
  private int countSameValue(Card gameCard, List<Player> players, int currentRound) {
    int counter = 0;
    for (Player player : players) {
      Card card = player.getDeck().getCards().get(currentRound);
      if (gameCard.getValue().equals(card.getValue()) && !gameCard.getColor().equals(card.getColor())) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Retourne le nombre de joueurs avec une carte totalement différente de celle du plateau
   *
   * @param gameCard
   * @param players
   * @param currentRound
   * @return nombre de cartes totalement différentes de celle du plateau
   */
  private int countNone(Card gameCard, List<Player> players, int currentRound) {
    int counter = 0;
    for (Player player : players) {
      Card card = player.getDeck().getCards().get(currentRound);
      if (!gameCard.getColor().equals(card.getColor()) && !gameCard.getValue().equals(card.getValue())) {
        counter++;
      }
    }
    return counter;
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
