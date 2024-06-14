/*
 * EntityManagerProvider.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.database.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Persistence;
import uppa.project.Global;

/**
 * Fournisseur d'EntityManager
 * Permet de récupérer une instance d'EntityManager
 * pour les DAO
 *
 * @see jakarta.persistence.EntityManager
 */
public final class EntityManagerProvider {
  private static String PERSISTENCE_UNIT_NAME = Global.PERSISTENCE_UNIT_NAME;

  private static EntityManager instance;
  private static EntityManagerFactory factory;

  public static EntityManager getInstance() {
    if (instance == null) {
      instance = getFactory().createEntityManager();
      instance.setFlushMode(FlushModeType.COMMIT);
    }
    return instance;
  }

  public static EntityManagerFactory getFactory() {
    if (factory == null) {
      factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    return factory;
  }

  public static void setPersitenceUnitName(String name) {
    PERSISTENCE_UNIT_NAME = name;
  }
  public static void close() {
    if (instance.isOpen()) instance.close();
    if (factory.isOpen()) factory.close();
  }
}
