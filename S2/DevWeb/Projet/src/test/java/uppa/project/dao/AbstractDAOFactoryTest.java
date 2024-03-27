package uppa.project.dao;

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
  void getDAOFactory() {
    assertEquals(Game_JPA_DAO_Factory.class, AbstractDAOFactory.getDAOFactory(PersistenceKind.JPA).getClass());

    assertThrows(NullPointerException.class, () -> AbstractDAOFactory.getDAOFactory(null));
  }
}
