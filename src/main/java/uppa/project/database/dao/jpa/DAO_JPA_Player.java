/*
 * DAO_JPA_Player.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import uppa.project.database.dao.DAO;
import uppa.project.database.dao.DAOException;
import uppa.project.database.dao.EntityManagerProvider;
import uppa.project.database.pojo.Player;

/**
 * DAO pour les joueurs
 *
 * @author Kévin Mitresse
 * @author Lucàs Vabre
 * @see Player
 * @see DAO
 */
public class DAO_JPA_Player extends DAO<Player> {

  /**
   * Gestionnaire d'entités
   */
  private final EntityManager entityManager;

  public DAO_JPA_Player() throws DAOException {
    this.entityManager = EntityManagerProvider.getInstance();
  }

  @Override
  public Player findById(int id) throws DAOException {
    return entityManager.find(Player.class, new BigDecimal(id));
  }

  @Override
  public Player[] findByField(String field, Object value) throws DAOException {
    String sqlQuery = String.format("SELECT p FROM Player p WHERE p.%s = (:val)", field);

    TypedQuery<Player> query = entityManager.createQuery(sqlQuery, Player.class);
    query.setParameter("val", value);
    List<Player> results = query.getResultList();
    return results.toArray(new Player[0]);
  }

  @Override
  public Player[] findAll() throws DAOException {
    TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p", Player.class);
    List<Player> results = query.getResultList();
    return results.toArray(new Player[0]);
  }

  @Override
  public Player create(Player data) throws DAOException {
    return update(data);
  }

  @Override
  public Player update(Player data) throws DAOException {
    return entityManager.merge(data);
  }

  @Override
  public void delete(Player data) throws DAOException {
    entityManager.remove(data);
  }
}
