/**
 * DAO_JPA_Game.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.pojo.Game;
import uppa.project.provider.EntityManagerProvider;

/**
 * DAO pour les parties de jeu
 *
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 * @see Game
 * @see DAO
 */
public class DAO_JPA_Game extends DAO<Game> {

  /**
   * Gestionnaire d'entités
   */
  private final EntityManager entityManager;

  /**
   * Constructeur par défaut
   *
   * @throws DAOException si une erreur survient lors de la création du DAO
   */
  public DAO_JPA_Game() throws DAOException {
    this.entityManager = EntityManagerProvider.getInstance();
  }

  @Override
  public Game findById(int id) throws DAOException {
    return entityManager.find(Game.class, new BigDecimal(id));
  }

  @Override
  public Game[] findByField(String field, Object value) throws DAOException {
    String sqlQuery = String.format("SELECT u FROM Game u WHERE u.%s = (:val)", field);

    TypedQuery<Game> query = entityManager.createQuery(sqlQuery, Game.class);
    query.setParameter("val", value);
    List<Game> results = query.getResultList();
    return results.toArray(new Game[0]);
  }

  @Override
  public Game[] findAll() throws DAOException {
    TypedQuery<Game> query = entityManager.createQuery("SELECT g FROM Game g", Game.class);
    List<Game> results = query.getResultList();
    return results.toArray(new Game[0]);
  }

  @Override
  public Game create(Game data) throws DAOException {
    return update(data);
  }

  @Override
  public Game update(Game data) throws DAOException {
    return entityManager.merge(data);
  }

  @Override
  public void delete(Game data) throws DAOException {
    entityManager.remove(data);
  }
}
