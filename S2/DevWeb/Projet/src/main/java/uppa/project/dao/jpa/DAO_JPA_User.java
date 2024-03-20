/*
 * DAO_JPA_User.java, 20/03/2024
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
import uppa.project.pojo.User;
import uppa.project.provider.EntityManagerProvider;

/**
 * DAO pour les utilisateurs
 *
 * @author Kévin Mitresse
 * @author Lucàs Vabre
 * @see User
 * @see DAO
 */
public class DAO_JPA_User extends DAO<User> {

  /**
   * Gestionnaire d'entités
   */
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

  public User[] findByField(String field, String value) throws DAOException {
    String sqlQuery = String.format("SELECT u FROM User u WHERE u.%s = (:val)", field);

    TypedQuery<User> query = entityManager.createQuery(sqlQuery, User.class);
    query.setParameter("val", value);
    List<User> results = query.getResultList();
    return results.toArray(new User[0]);
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
