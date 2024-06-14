package uppa.project.json.websocket;

import uppa.project.database.pojo.User;

public class SimpleUser {
  private final int id;
  private final String username;

  private final int nbPlayedGames;
  private final int nbWin;
  private final double winRate;
  private final double scoreRate;

  private final double rigthClickPercentRate;
  private final double rapidClickPercentRate;

  public SimpleUser(User user) {
    this.id = user.getId().intValue();
    this.username = user.getUsername();
    this.nbPlayedGames = user.getNbPlayedGame();
    this.nbWin = user.getNbWin();
    this.winRate = user.getWinRate();
    this.scoreRate = user.getScoreRate();
    this.rigthClickPercentRate = user.getRightClickPercentRate();
    this.rapidClickPercentRate = user.getRapidClickPercentRate();
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public int getNbPlayedGames() {
    return nbPlayedGames;
  }

  public int getNbWin() {
    return nbWin;
  }

  public double getWinRate() {
    return winRate;
  }

  public double getScoreRate() {
    return scoreRate;
  }

  public double getRigthClickPercentRate() {
    return rigthClickPercentRate;
  }

  public double getRapidClickPercentRate() {
    return rapidClickPercentRate;
  }
}
