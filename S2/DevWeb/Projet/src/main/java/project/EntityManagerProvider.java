package project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class EntityManagerProvider {

  private static EntityManager instance;
  private static EntityManagerFactory factory;
  private final static String PERSISTANCE_UNIT_NAME = "default";

  public static EntityManager getInstance() {
    if (factory == null) {
      factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
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
