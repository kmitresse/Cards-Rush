package uppa.project.json.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import uppa.project.database.pojo.Card;
import uppa.project.database.pojo.Player;
import uppa.project.web.translation.Translator;

public class SimplePlayer {

  private final SimpleUser user;
  private final int gameId;
  private final int score;
  private final boolean winner;
  private final String winnerUsername;
  private final String createdDate;
  private final int clickCount;
  private final int rightClickCount;
  private final int partialRightClickCount;
  private final int rapidClickCount;
  private final Card currentCard;

  public SimplePlayer(Player player, int currentRound) {
    this.user = new SimpleUser(player.getUser());
    this.score = player.getScore();
    this.winner = player.isWinner();
    this.winnerUsername = "";
    this.clickCount = player.getClickCount();
    this.rightClickCount = player.getRightClickCount();
    this.rapidClickCount = player.getRapidClickCount();
    this.partialRightClickCount = player.getPartialRightClickCount();
    this.currentCard = player.getDeck().getCards().get((partialRightClickCount+rightClickCount) % player.getDeck().getCards().size());
    this.createdDate = "";
    this.gameId = -1;
  }


  /**
   * Constructeur pour la pagination
   *
   * @param player le joueur
   * @param translator le traducteur
   */
  public SimplePlayer(Player player, Translator translator) {
    this.user = new SimpleUser(player.getUser());
    this.gameId = player.getGame().getId().intValue();
    this.score = player.getScore();
    this.winner = player.isWinner();
    this.winnerUsername = player.getGame().getWinner().getUser().getUsername();
    this.clickCount = player.getClickCount();
    this.rightClickCount = player.getRightClickCount();
    this.rapidClickCount = player.getRapidClickCount();
    this.partialRightClickCount = player.getPartialRightClickCount();
    this.currentCard = null;
    Date date = player.getGame().getCreatedAt();
    String language = translator.getLanguage();
    SimpleDateFormat sdfDay;
    SimpleDateFormat sdfHour;
    if (translator.getLanguage().equals("EN")) {
      sdfDay = new SimpleDateFormat("MM/dd/yyyy");
      sdfHour = new SimpleDateFormat("HH:mm");
    } else {
      sdfDay = new SimpleDateFormat("dd/MM/yyyy");
      sdfHour = new SimpleDateFormat("HH:mm");
    }
    String day = sdfDay.format(date);
    String hour = sdfHour.format(date);
    this.createdDate = day + ',' + hour ;
  }

  public SimplePlayer(Player player) {
    this.user = new SimpleUser(player.getUser());
    this.score = player.getScore();
    this.winner = player.isWinner();
    this.clickCount = player.getClickCount();
    this.rightClickCount = player.getRightClickCount();
    this.partialRightClickCount = player.getPartialRightClickCount();
    this.rapidClickCount = player.getRapidClickCount();
    this.currentCard = null;
    this.winnerUsername = "";
    this.createdDate = "" ;
    this.gameId = -1;

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

  public int getPartialRightClickCount() {
    return partialRightClickCount;
  }

  public int getRightClickCount() {
    return rightClickCount;
  }

  public int getRapidClickCount() {
    return rapidClickCount;
  }
}
