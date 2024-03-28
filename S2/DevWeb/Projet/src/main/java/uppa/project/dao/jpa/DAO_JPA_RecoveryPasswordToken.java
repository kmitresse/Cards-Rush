/*
 * DAO_JPA_RecoveryPasswordToken.java, 20/03/2024
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
import uppa.project.pojo.RecoveryPasswordToken;
import uppa.project.provider.EntityManagerProvider;

/**
 * DAO pour les tokens de récupération de mot de passe
 *
 * @author Kévin Mitresse
 * @author Lucàs Vabre
 * @see RecoveryPasswordToken
 * @see DAO
 */
public class DAO_JPA_RecoveryPasswordToken extends DAO<RecoveryPasswordToken> {

  /**
   * Gestionnaire d'entités
   */
  private final EntityManager entityManager;

  public DAO_JPA_RecoveryPasswordToken() throws DAOException {
    this.entityManager = EntityManagerProvider.getInstance();
  }

  @Override
  public RecoveryPasswordToken findById(int id) throws DAOException {
    return entityManager.find(RecoveryPasswordToken.class, new BigDecimal(id));
  }

  public RecoveryPasswordToken[] findByField(String field, Object value) throws DAOException {
    String sqlQuery = String.format("SELECT r FROM RecoveryPasswordToken r WHERE r.%s = (:val)", field);

    TypedQuery<RecoveryPasswordToken> query = entityManager.createQuery(sqlQuery, RecoveryPasswordToken.class);
    query.setParameter("val", value);
    List<RecoveryPasswordToken> results = query.getResultList();
    return results.toArray(new RecoveryPasswordToken[0]);
  }

  @Override
  public RecoveryPasswordToken[] findAll() throws DAOException {
    TypedQuery<RecoveryPasswordToken> query = entityManager.createQuery("SELECT r FROM RecoveryPasswordToken r", RecoveryPasswordToken.class);
    List<RecoveryPasswordToken> results = query.getResultList();
    return results.toArray(new RecoveryPasswordToken[0]);
  }

  @Override
  public void create(RecoveryPasswordToken data) throws DAOException {
    update(data);
  }

  @Override
  public void update(RecoveryPasswordToken data) throws DAOException {
    entityManager.merge(data);
  }

  @Override
  public void delete(RecoveryPasswordToken data) throws DAOException {
    entityManager.remove(data);
  }
}
