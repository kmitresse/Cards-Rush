package uppa.project.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import uppa.project.EntityManagerProvider;
import uppa.project.dao.DAO;
import uppa.project.dao.DAOException;
import uppa.project.pojo.Player;

public class DAO_JPA_Player extends DAO<Player> {

  private final EntityManager entityManager;

  public DAO_JPA_Player() throws DAOException {
    this.entityManager = EntityManagerProvider.getInstance();
  }

  @Override
  public Player findById(int id) throws DAOException {
    Player result =  entityManager.find(Player.class, new BigDecimal(id));
    entityManager.flush();
    return result;
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
