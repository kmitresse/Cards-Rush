package project;

import java.util.Calendar;
import project.dao.DAO;
import project.dao.DAOException;
import project.dao.jpa.Game_JPA_DAO_Factory;
import project.pojo.Game;
import project.pojo.Player;
import project.pojo.User;

public class Main {

  public static void main(String[] args) {
    try {
      Game_JPA_DAO_Factory jpaDaoFactory = new Game_JPA_DAO_Factory();
      DAO<User> daoJpaUser = jpaDaoFactory.getDAOUser();
      DAO<Game> daoJpaGame = jpaDaoFactory.getDAOGame();
      DAO<Player> daoJpaPlayer = jpaDaoFactory.getDAOPlayer();

    // Contenu de la BD au début
      User[] users = daoJpaUser.findAll();
      for (User u : users) {
        System.out.println(u.toString());
      }
      System.out.println();

    // Ajout d'un joueur :
      Calendar calendarUser1 = Calendar.getInstance();
      calendarUser1.set(1996,Calendar.FEBRUARY,20);

      User user1 = new User("Kevin", "Mitresse", calendarUser1.getTime(), User.Sexe.HOMME);

      Calendar calendarUser2 = Calendar.getInstance();
      calendarUser2.set(2002,Calendar.JUNE,28);

      User user2 = new User("Lucàs", "Vabre", calendarUser1.getTime(), User.Sexe.HOMME);

      users = daoJpaUser.findAll();
      for (User u : users) {
        System.out.println(u.toString());
      }
      System.out.println();

      EntityManagerProvider.close();
    } catch (DAOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Error : : : : ");
    }
  }
}
