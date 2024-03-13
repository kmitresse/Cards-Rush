/*
 * DAO.java, 21/02/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.dao;

/**
 * DAO abstrait et générique pour tout type de données
 *
 * @param <D> la classe paramétrant le DAO
 */
public abstract class DAO<D> {

  public void DAO() throws DAOException {

  }

  /**
   * Retourne à partir du support de persistance un objet en fonction de son identifiant
   *
   * @param id identifiant de l'objet
   * @return l'instance de l'objet
   * @throws DAOException en cas de problème
   */
  public abstract D findById(int id) throws DAOException;

  public abstract D[] findAll() throws DAOException;

  /**
   * Rend persistant un objet qui n'avait pas encore de réprésentation sur le support de persistance
   *
   * @param data l'objet à rendre persistant
   * @throws DAOException en cas de problème
   */
  public abstract void create(D data) throws DAOException;

  /**
   * Met à jour le contenu correspondant à l'objet sur le support persistant (l'objet
   * avait déjà une représentation sur le support persistant)
   *
   * @param data l'objet modifié dont le contenu est à mettre à jour
   * @throws DAOException en cas de problème
   */
  public abstract void update(D data) throws DAOException;

  /**
   * Efface du support persistant le contenu équivalent à l'objet
   *
   * @param data l'objet à supprimer
   * @throws DAOException en cas de problème
   */
  public abstract void delete(D data) throws DAOException;
}
