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
    Game result = entityManager.find(Game.class, new BigDecimal(id));
    entityManager.flush();
    return result;
  }

  @Override
  public Game[] findByField(String field, String value) throws DAOException {
    TypedQuery<Game> query = entityManager.createQuery("SELECT u FROM Game u WHERE ?1=?2", Game.class);
    query.setParameter(1, field);
    query.setParameter(2, value);
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
  public void create(Game data) throws DAOException {
    update(data);
  }

  @Override
  public void update(Game data) throws DAOException {
    entityManager.getTransaction().begin();
    entityManager.merge(data);
    entityManager.getTransaction().commit();
  }

  @Override
  public void delete(Game data) throws DAOException {
    entityManager.getTransaction().begin();
    entityManager.remove(data);
    entityManager.getTransaction().commit();
  }
}
