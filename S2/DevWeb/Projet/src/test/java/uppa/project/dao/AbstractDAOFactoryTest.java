package uppa.project.dao;

import java.util.HashMap;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uppa.project.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.provider.EntityManagerProvider;

import static org.junit.jupiter.api.Assertions.*;
class AbstractDAOFactoryTest {

  @BeforeAll
  static void setUp() {
    EntityManagerProvider.setPersitenceUnitName("test");
  }

  @Test
  void test_getDAOFactory() {
    // If the method throws an exception, make sure the database is running
    assertDoesNotThrow(() -> AbstractDAOFactory.getDAOFactory(PersistenceKind.JPA));

    // Test if the method returns the correct DAO factory
    HashMap<PersistenceKind, Class> factories = new HashMap<>() {{
      put(PersistenceKind.JPA, Game_JPA_DAO_Factory.class);
    }};

    for (PersistenceKind kind : PersistenceKind.values()) {
      assertEquals(
        factories.get(kind),
        Objects.requireNonNull(AbstractDAOFactory.getDAOFactory(kind)).getClass()
      );
    }
  }
}
