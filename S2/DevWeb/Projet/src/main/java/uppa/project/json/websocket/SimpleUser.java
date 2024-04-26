package uppa.project.json.websocket;

import uppa.project.database.pojo.User;

public class SimpleUser {
  private final int id;
  private final String username;

  public SimpleUser(User user) {
    this.id = user.getId().intValue();
    this.username = user.getUsername();
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }
}