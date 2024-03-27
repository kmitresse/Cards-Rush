package uppa.project.provider;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityManagerProviderTest {

  @BeforeAll
  static void setUp() {
    EntityManagerProvider.setPersitenceUnitName("test");
  }

  @Test
  void getInstance() {
    EntityManager entity1 = EntityManagerProvider.getInstance();
    EntityManager entity2 = EntityManagerProvider.getInstance();

    assertNotNull(entity1);
    assertNotNull(entity2);
    assertEquals(entity1, entity2);
  }

  @Test
  void close() {
    EntityManager entity = EntityManagerProvider.getInstance();
    EntityManagerProvider.close();

    assertFalse(entity::isOpen);
  }
}
