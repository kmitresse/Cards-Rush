package project.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import project.dao.DAO;
import project.dao.DAOException;
import project.EntityManagerProvider;
import project.pojo.User;

public class DAO_JPA_User extends DAO<User> {

  private final EntityManager entityManager;

  public DAO_JPA_User() throws DAOException {
    this.entityManager = EntityManagerProvider.getInstance();
  }

  @Override
  public User findById(int id) throws DAOException {
    User result =  entityManager.find(User.class, new BigDecimal(id));
    entityManager.flush();
    return result;
  }

  @Override
  public User[] findAll() throws DAOException {
    TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
    List<User> results = query.getResultList();
    return results.toArray(new User[0]);
  }

  @Override
  public void create(User data) throws DAOException {
    update(data);
  }

  @Override
  public void update(User data) throws DAOException {
    entityManager.getTransaction().begin();
    entityManager.merge(data);
    entityManager.getTransaction().commit();
  }

  @Override
  public void delete(User data) throws DAOException {
    entityManager.getTransaction().begin();
    entityManager.remove(data);
    entityManager.getTransaction().commit();
  }
}
