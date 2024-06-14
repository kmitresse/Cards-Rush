/*
 * NewGameBean.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */
package uppa.project.bean;

import jakarta.persistence.EntityManager;
import java.io.Serial;
import java.io.Serializable;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.database.pojo.Game;
import uppa.project.json.HttpResponse;
import uppa.project.json.HttpResponseCode;
import uppa.project.web.translation.Translator;

public class NewGameBean implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String difficulty;
  private String nbRounds;
  private String timer;
  private String nbValues;
  private String nbColors;
  private Translator translator;

  private Game game;

  private HttpResponse error;

  public NewGameBean() {
  }

  /**
   * Validation des paramètres de la requête
   *
   * @return true si les paramètres sont valides, false sinon
   */
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
      error = new HttpResponse(HttpResponseCode.BAD_REQUEST, translator.translate("newGame_invalid_param_values"));
    } catch (DAOException e) {
      entityManager.getTransaction().rollback();
      error = new HttpResponse(HttpResponseCode.INTERNAL_SERVER_ERROR,  translator.translate("newGame_error_creation"));
    }
    return false;
  }

  /**
   *
   * @param difficulty la difficulté de la partie
   * @return l'entité
   */
  public NewGameBean setDifficulty(String difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   *
   * @param nbRounds le nombre de tours de la partie
   * @return l'entité
   */
  public NewGameBean setNbRounds(String nbRounds) {
    this.nbRounds = nbRounds;
    return this;
  }

  /**
   *
   * @param timer le temps de jeu de la partie
   * @return l'entité
   */
  public NewGameBean setTimer(String timer) {
    this.timer = timer;
    return this;
  }

  /**
   *
   * @param nbValues le nombre de valeurs de la partie
   * @return l'entité
   */
  public NewGameBean setNbValues(String nbValues) {
    this.nbValues = nbValues;
    return this;
  }

  /**
   *
   * @param nbColors le nombre de couleurs de la partie
   * @return l'entité
   */
  public NewGameBean setNbColors(String nbColors) {
    this.nbColors = nbColors;
    return this;
  }

  /**
   *
   * @param translator le traducteur
   * @return l'entité
   */
  public NewGameBean setTranslator(Translator translator) {
    this.translator = translator;
    return this;
  }
  /**
   *
   * @return la partie créée
   */
  public Game getGame() {
    return game;
  }

  /**
   *
   * @return l'erreur
   */
  public HttpResponse getError() {
    return error;
  }
}
