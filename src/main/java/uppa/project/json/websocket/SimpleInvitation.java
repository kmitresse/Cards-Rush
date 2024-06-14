package uppa.project.json.websocket;

public class SimpleInvitation {

  private final SimpleUser from;

  private final SimpleUser to;

  private final int game_id;

  public SimpleInvitation(SimpleUser from, SimpleUser to, int game_id) {
    this.from = from;
    this.to = to;
    this.game_id = game_id;
  }

  public SimpleUser getFrom() {
    return from;
  }

  public SimpleUser getTo() {
    return to;
  }

  public int getGameId() {
    return game_id;
  }
}