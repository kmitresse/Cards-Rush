/*
 * DAO_JPA_User.java, 20/03/2024
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
import uppa.project.database.pojo.User;
import uppa.project.database.dao.EntityManagerProvider;

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
    return entityManager.find(User.class, new BigDecimal(id));
  }

  public User[] findByField(String field, Object value) throws DAOException {
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
  public User create(User data) throws DAOException {
    return update(data);
  }

  @Override
  public User update(User data) throws DAOException {
    return entityManager.merge(data);
  }

  @Override
  public void delete(User data) throws DAOException {
    entityManager.remove(data);
  }
}
