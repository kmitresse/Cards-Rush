package project.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import project.EntityManagerProvider;
import project.dao.DAO;
import project.dao.DAOException;
import project.pojo.Game;

public class DAO_JPA_Game extends DAO<Game> {

  private final EntityManager entityManager;

  public DAO_JPA_Game() throws DAOException {
    this.entityManager = EntityManagerProvider.getInstance();
  }

  @Override
  public Game findById(int id) throws DAOException {
    Game result =  entityManager.find(Game.class, new BigDecimal(id));
    entityManager.flush();
    return result;
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
