/*
 * EntityManagerProvider.java, 20/03/2024
 * UPPA M1 TI 2023-2024
 * Pas de copyright, aucun droits
 */

package uppa.project.provider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Fournisseur d'EntityManager
 * Permet de récupérer une instance d'EntityManager
 * pour les DAO
 *
 * @see jakarta.persistence.EntityManager
 */
public final class EntityManagerProvider {
//  private static final String PERSISTENCE_UNIT_NAME = DotenvProvider.getInstance().get("PERSISTENCE_UNIT_NAME");
  private static final String PERSISTENCE_UNIT_NAME = "prod";

  private static EntityManager instance;
  private static EntityManagerFactory factory;

  public static EntityManager getInstance() {
    if (factory == null) {
      factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    if (instance == null) {
      instance = factory.createEntityManager();
    }
    return instance;
  }

  public static void close() {
    if (instance.isOpen()) instance.close();
    if (factory.isOpen()) factory.close();
  }
}
