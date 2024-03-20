/*
 * DAO_JPA_Player.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao.jpa;

import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.pojo.Player;

/**
 * DAO pour les joueurs
 *
 * @author Kévin Mitresse
 * @author Lucàs Vabre
 * @see Player
 * @see DAO
 */
public class DAO_JPA_Player extends DAO<Player> {

  public DAO_JPA_Player() throws DAOException {
    super();
  }

  @Override
  public Player findById(int id) throws DAOException {
    Player result =  entityManager.find(Player.class, new BigDecimal(id));
    entityManager.flush();
    return result;
  }

  @Override
  public Player[] findByField(String field, String value) throws DAOException {
    TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p WHERE ?1=?2", Player.class);
    query.setParameter(1, field);
    query.setParameter(2, value);
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
  public void create(Player data) throws DAOException {
    update(data);
  }

  @Override
  public void update(Player data) throws DAOException {
    entityManager.getTransaction().begin();
    entityManager.merge(data);
    entityManager.getTransaction().commit();
  }

  @Override
  public void delete(Player data) throws DAOException {
    entityManager.getTransaction().begin();
    entityManager.remove(data);
    entityManager.getTransaction().commit();
  }
}
