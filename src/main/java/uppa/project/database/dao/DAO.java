/*
 * DAO.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import uppa.project.database.pojo.Player;

/**
 * DAO abstrait et générique pour tout type de données
 *
 * @param <D> la classe paramétrant le DAO
 * @author Kevin Mitressé
 * @author Lucàs Vabre
 */
public abstract class DAO<D> {

  /**
   * Constructeur par défaut
   *
   * @throws DAOException si une erreur survient lors de la création du DAO
   */
  public DAO() throws DAOException {}

  /**
   * Retourne l'objet {@link D} en fonction de son identifiant
   *
   * @param id identifiant de l'objet
   * @return l'instance de l'objet {@link D}
   * @throws DAOException en cas de problème
   * @see D
   */
  public abstract D findById(int id) throws DAOException;

  /**
   * Retourne tous les objets {@link D} paramétrant le DAO
   *
   * @return un tableau d'objets {@link D}
   * @throws DAOException en cas de problème
   * @see D
   */
  public abstract D[] findAll() throws DAOException;

  /**
   * Rend persistant un objet {@link D} qui n'avait pas encore de réprésentation sur le support de persistance
   *
   * @param data l'objet {@link D} à rendre persistant
   * @throws DAOException en cas de problème
   * @see D
   */
  public abstract D create(D data) throws DAOException;

  /**
   * Met à jour le contenu correspondant à l'objet {@link D} sur le support persistant (l'objet
   * avait déjà une représentation sur le support persistant)
   *
   * @param data l'objet {@link D} modifié dont le contenu est à mettre à jour
   * @throws DAOException en cas de problème
   * @see D
   */
  public abstract D update(D data) throws DAOException;

  /**
   * Efface du support persistant le contenu équivalent à l'objet {@link D}
   *
   * @param data l'objet {@link D} à supprimer
   * @throws DAOException en cas de problème
   * @see D
   */
  public abstract void delete(D data) throws DAOException;

  /**
   * Retourne les objets {@link D} paramétrant le DAO en fonction d'un champ et d'une valeur
   *
   * @param field le champ à rechercher
   * @param value la valeur à rechercher
   * @return un tableau d'objets {@link D}
   * @throws DAOException en cas de problème
   * @see D
   */
  public abstract D[] findByField(String field, Object value) throws DAOException;

  public Player[] findPlayerForPagination(int userId, int firstData) throws DAOException {
    EntityManager entityManager = EntityManagerProvider.getInstance();
    TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p WHERE p.user.id = (:user) ORDER BY p.id DESC", Player.class);
    query.setParameter("user", userId);
    query.setFirstResult(firstData);
    query.setMaxResults(5);
    List<Player> results = query.getResultList();
    return results.toArray(new Player[0]);
  }
}
