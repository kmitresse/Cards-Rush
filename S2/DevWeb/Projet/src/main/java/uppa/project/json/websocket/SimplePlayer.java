package uppa.project.json.websocket;

import uppa.project.database.pojo.Card;
import uppa.project.database.pojo.Player;

public class SimplePlayer {

  private final SimpleUser user;
  private final int score;
  private final boolean winner;
  private final int clickCount;
  private final int rightClickCount;
  private final int rapidClickCount;
  private final Card currentCard;

  public SimplePlayer(Player player, int currentRound) {
    this.user = new SimpleUser(player.getUser());
    this.score = player.getScore();
    this.winner = player.isWinner();
    this.clickCount = player.getClickCount();
    this.rightClickCount = player.getRightClickCount();
    this.rapidClickCount = player.getRapidClickCount();

    this.currentCard = player.getDeck().getCards().get(currentRound % player.getDeck().getCards().size());
  }

  public SimplePlayer(Player player) {
    this.user = new SimpleUser(player.getUser());
    this.score = player.getScore();
    this.winner = player.isWinner();
    this.clickCount = player.getClickCount();
    this.rightClickCount = player.getRightClickCount();
    this.rapidClickCount = player.getRapidClickCount();
    this.currentCard = null;
  }

  public Card getCurrentCard() {
    return currentCard;
  }

  public SimpleUser getUser() {
    return user;
  }

  public int getScore() {
    return score;
  }

  public boolean isWinner() {
    return winner;
  }

  public int getClickCount() {
    return clickCount;
  }

  public int getRightClickCount() {
    return rightClickCount;
  }

  public int getRapidClickCount() {
    return rapidClickCount;
  }
}
