package uppa.project;

import java.util.Calendar;
import uppa.project.dao.DAO;
import uppa.project.dao.jpa.Game_JPA_DAO_Factory;
import uppa.project.pojo.Game;
import uppa.project.pojo.Player;
import uppa.project.pojo.User;

public class Main {

  public static void main(String[] args) {
    try {
      Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
      DAO<User> daoJpaUser = jpaDaoFactory.getDAOUser();
//      DAO<Game> daoJpaGame = jpaDaoFactory.getDAOGame();
//      DAO<Player> daoJpaPlayer = jpaDaoFactory.getDAOPlayer();

      // Contenu de la BD au début
      User[] users = daoJpaUser.findAll();
      for (User u : users) {
        System.out.println(u.toString());
      }
      System.out.println();

      // Ajout d'User :
      Calendar cal1 = Calendar.getInstance();
      cal1.set(1996, Calendar.FEBRUARY, 20);
      User user1 = new User("Kevin", "Mitresse", cal1.getTime(), User.Gender.MALE);

      Calendar cal2 = Calendar.getInstance();
      cal2.set(2002, Calendar.JUNE, 28);
      User user2 = new User("Lucàs", "Vabre", cal2.getTime(), User.Gender.MALE);

      daoJpaUser.create(user1);
      daoJpaUser.create(user2);

      // Contenu de la BD après ajout
      users = daoJpaUser.findAll();
      for (User u : users) {
        System.out.println(u.toString());
      }
      System.out.println();

      EntityManagerProvider.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
