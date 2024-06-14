package uppa.project.json.websocket;

import java.util.ArrayList;
import uppa.project.database.pojo.Card;
import uppa.project.database.pojo.Game;
import uppa.project.database.pojo.Player;

public class SimpleGame {

  private final int id;
  private final ArrayList<SimplePlayer> players;
  private final Card currentCard;
  private final int currentRound;

  public SimpleGame(Game game, ArrayList<Player> playerArrayList) {
    this.id = game.getId().intValue();
    this.players = new ArrayList<>();
    for (Player p : playerArrayList) players.add(new SimplePlayer(p, game.getCurrentRound()));
    this.currentCard = game.getDeck().getCards().get(game.getCurrentRound() % game.getDeck().getCards().size());
    this.currentRound  = game.getCurrentRound();
  }

  public int getId() {
    return id;
  }

  public ArrayList<SimplePlayer> getPlayers() {
    return players;
  }

  public Card getCurrentCard() {
    return currentCard;
  }

  public int getCurrentRound() {
    return currentRound;
  }
}
