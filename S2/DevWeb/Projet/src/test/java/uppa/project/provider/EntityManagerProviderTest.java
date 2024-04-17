package uppa.project.provider;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uppa.project.database.dao.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;

class EntityManagerProviderTest {

  @BeforeAll
  static void setUp() {
    EntityManagerProvider.setPersitenceUnitName("test");
  }

  @Test
  void test_getInstance() {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(EntityManagerProvider::getInstance);

    // Check if the object is unique
    EntityManager entity1 = EntityManagerProvider.getInstance();
    EntityManager entity2 = EntityManagerProvider.getInstance();

    assertNotNull(entity1);
    assertNotNull(entity2);
    assertEquals(entity1, entity2);
  }

  @Test
  void test_close() {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(EntityManagerProvider::getInstance);

    // Check if the object is closed
    EntityManager entity = EntityManagerProvider.getInstance();
    EntityManagerProvider.close();

    assertFalse(entity::isOpen);
  }
}
