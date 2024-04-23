package uppa.project.bean;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Game;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;

public class NewGameBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String difficulty;
  private String nbRounds;
  private String timer;
  private String nbValues;
  private String nbColors;

  private Game game;

  private HttpResponse error;

  public NewGameBean() {
  }

  public boolean validate() {
    EntityManager entityManager = EntityManagerProvider.getInstance();

    try {
      Game game = new Game(
        Game.Difficulty.valueOf(difficulty),
        Integer.parseInt(timer),
        Integer.parseInt(nbRounds),
        Integer.parseInt(nbColors),
        Integer.parseInt(nbValues)
      );

      // Sauvegarder la game en base de données
      DAO<Game> gameDAO = new Game_JPA_DAO_Factory().getDAOGame();

      entityManager.getTransaction().begin();
      this.game = gameDAO.create(game);
      entityManager.getTransaction().commit();
      return true;
    } catch (NumberFormatException e) {
      error = new HttpResponse(HttpResponseCode.BAD_REQUEST, "Les valeurs entrées ne sont pas valides");
    } catch (DAOException e) {
      entityManager.getTransaction().rollback();
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR,  "Erreur lors de la création de la partie");
    }
    return false;
  }

  public NewGameBean setDifficulty(String difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  public NewGameBean setNbRounds(String nbRounds) {
    this.nbRounds = nbRounds;
    return this;
  }

  public NewGameBean setTimer(String timer) {
    this.timer = timer;
    return this;
  }

  public NewGameBean setNbValues(String nbValues) {
    this.nbValues = nbValues;
    return this;
  }

  public NewGameBean setNbColors(String nbColors) {
    this.nbColors = nbColors;
    return this;
  }

  public Game getGame() {
    return game;
  }

  public HttpResponse getError() {
    return error;
  }
}
